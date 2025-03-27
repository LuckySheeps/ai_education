package com.ai_education.handler;

import com.ai_education.exception.BaseException;
import com.ai_education.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        return Result.error(ex.getMessage());
    }

    /**
     * 处理sql异常
     * @param ex
     * @return
     */

    //处理一些数据库查询返回问题
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<Result> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(ex.getMessage()));
    }

    //处理事务异常
    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    public ResponseEntity<Result> handleTransactionException(TransactionSystemException ex) {
        // 捕获事务异常并返回错误信息
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.error("事务异常: " + ex.getRootCause().getMessage()));
    }


    //处理其他异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Result> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.error("An unexpected error occurred: " + ex.getMessage()));
    }


}
