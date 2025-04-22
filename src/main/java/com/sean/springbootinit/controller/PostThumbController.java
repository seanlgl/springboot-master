package com.sean.springbootinit.controller;
import com.sean.springbootinit.common.BaseResponse;
import com.sean.springbootinit.common.ErrorCode;
import com.sean.springbootinit.common.ResultUtils;
import com.sean.springbootinit.exception.BusinessException;
import com.sean.springbootinit.model.dto.postthumb.PostThumbAddRequest;
import com.sean.springbootinit.model.entity.User;
import com.sean.springbootinit.service.PostThumbService;
import com.sean.springbootinit.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 */
@Api(tags = "帖子点赞")
@RestController
@RequestMapping("/post_thumb")
@Slf4j
public class PostThumbController {

    @Resource
    private PostThumbService postThumbService;

    @Resource
    private UserService userService;

    @ApiOperation(value = "点赞 / 取消点赞" )
    @PostMapping("/")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddRequest postThumbAddRequest, HttpServletRequest request) {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.doPostThumb(postId, loginUser);
        return ResultUtils.success(result);
    }

}
