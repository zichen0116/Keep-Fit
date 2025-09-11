package com.equipmentwork.exception;


import com.equipmentwork.entity.Result;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
// 全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错~", e);
        return Result.error("程序异常~");

    }

    // @ExceptionHandler
    // public Result handleDuplicateKeyException(DuplicateKeyException e) {
    //     log.error("程序出错了~", e);
    //     String message = e.getMessage();
    //     int duplicateEntry = message.indexOf("Duplicate entry");
    //     String errMsg = message.substring(duplicateEntry);
    //     String[] arr = errMsg.split(" ");
    //     return Result.error(arr[2] + "已存在");
    //
    // }

    @ExceptionHandler
    public Result handleHasReservationException(EquipReservationException e) {
        log.error("程序出错~", e);
        return Result.error("对不起, 该器材下有预约, 不能直接删除");
    }

    @ExceptionHandler
    public Result handleHasRepairException(EquipRepairException e) {
        log.error("程序出错了~", e);
        return Result.error("对不起, 该器材有维修记录, 不能直接删除");
    }



}