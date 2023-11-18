package com.azure.azure_merge.controller;

import com.azure.azure_merge.common.BaseContext;
import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.FolderUser;
import com.azure.azure_merge.entity.Record;
import com.azure.azure_merge.entity.Vips;
import com.azure.azure_merge.mapper.FolderUserMapper;
import com.azure.azure_merge.mapper.RecordMapper;
import com.azure.azure_merge.service.FolderUserService;
import com.azure.azure_merge.service.RecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/folderuser-record")
@Slf4j
@Api(tags = "收藏和记录管理相关的接口")
public class FolderUserController {

    @Autowired
    private FolderUserService folderUserService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private FolderUserMapper folderUserMapper;
    @Autowired
    private RecordMapper recordMapper;


    @ApiOperation(value = "显示该用户拥有的所有收藏夹", notes = "folder_user根据用户user_id返回所有对应收藏夹名List<folder_name>")
    @GetMapping("/page/all-folder")
    public CommonResult<Page> pageFolder(int page, int pageSize) {
        log.info("page = {},pageSize = {},user_id = {},create_date = {},folder_name = {}",
                page, pageSize);
        Page<FolderUser> folderUserPage = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<FolderUser> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(FolderUser::getUserId, userId);
        int userStatus = queryWrapper1.getEntity().getIsDeleted();
        if (userStatus == 0) {
            queryWrapper1.eq(FolderUser::getUserId, userId);
        }
        folderUserService.page(folderUserPage, queryWrapper1);
        return CommonResult.success(folderUserPage);
    }

//    //显示该用户拥有的所有收藏夹：folder_user根据用户user_id返回所有对应收藏夹名List<folder_name>
//    @GetMapping("/folderuser-list/{user_id}")
//    public List<String> FUList_All(@PathVariable("user_id") Integer user_id){
//        return folderUserMapper.selectFNameByUId(user_id);
//    }


//    //搜索该用户查找的收藏夹：
//    //（folder_user根据folder_name返回指定folder_id）
//    //folder_user根据folder_id搜索用户user_id对应收藏夹全部信息
//    @GetMapping("/search-folder/{folder_name}")
//    public FolderUser FUListByFName(@PathVariable("folder_name") String folder_name,
//                                    @Param("user_id") Integer user_id){
//        return folderUserRecordService.getFolderByFIdUId(folder_name,user_id);
//    }
//
//
//
//    //用户点击查看收藏夹内收藏内容：
//    //（folder_user根据folder_name返回指定folder_id）
//    //record根据folder_id和user_id返回关联的所有记录
//    @GetMapping("/{folder_name}/record-list")
//    public List<Record> RListByFName(@PathVariable("folder_name") String folder_name,
//                                     @Param("user_id") Integer user_id){
//        return folderUserRecordService.getRecordByFIdUId(folder_name,user_id);
//    }


    @ApiOperation(value = "用户新建收藏夹", notes = "folder_user插入一条记录")
    @PostMapping("/folderuser-insert")
    public CommonResult<String> FolderInsert(@RequestBody FolderUser folderUser) {
        log.info("folder_user={}", folderUser);
        FolderUser new_folderuser = new FolderUser();
        new_folderuser.setFolderId(folderUser.getFolderId());
        new_folderuser.setUserId(folderUser.getUserId());
        new_folderuser.setCreateDate(LocalDateTime.now());
        new_folderuser.setIsDeleted(0);
        folderUserService.save(new_folderuser);
        return CommonResult.success("新建成功");
    }


    @ApiOperation(value = "用户删除指定收藏夹", notes = "folder_user根据user_id和folder_id删除一条记录")
    @PutMapping("/folder-delete")
    public CommonResult<String> folderDelete(int folderId) {
        log.info("folder_id = {}", folderId);

        LambdaUpdateWrapper<FolderUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(FolderUser::getFolderId, folderId)
                .set(FolderUser::getIsDeleted, 1);//状态置1，表示已移除
        UpdateWrapper<Record> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("folder_id", folderId).set("is_deleted", 1);//记录状态置1，表示已移除
        return CommonResult.success("删除成功");
    }


    @ApiOperation(value = "用户新增收藏记录", notes = "record插入一条新纪录")
    @PostMapping("/record-insert")
    public CommonResult<String> RecordInsert(@RequestBody Record record) {
        log.info("record={}", record);
        Record new_record = new Record();
        new_record.setIsDeleted(0);
        new_record.setRecordId(record.getRecordId());
        new_record.setResourceId(record.getResourceId());
        new_record.setUserId(record.getUserId());
        new_record.setFolderId(record.getFolderId());
        new_record.setCollectTime(LocalDateTime.now());
        new_record.setResourceType(record.getResourceType());
        recordService.save(new_record);
        return CommonResult.success("新建成功");
    }

}
