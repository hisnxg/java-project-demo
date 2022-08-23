package com.nxg.upload.controller;

import com.nxg.upload.domain.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author nxg
 * date 2022/3/9
 * @apiNote
 * User  post 新增使用post
 * {id}  delete 删除使用delete
 * {id}  put 更新
 * {id}  get 根据id加载
 * list-page  post 分页查询
 */

@RestController
@RequestMapping("/user")
@Api(tags = {"用户管理API"})
public class UserController {

    @PostMapping
    @ApiOperation(value = "新增用户",notes = "新增返回当前用户")
    @ApiResponses({@ApiResponse(code = 200,message = "返回成功",response = User.class),
                    @ApiResponse(code = 400,message = "参数没有填好{id =1}",response = User.class),
            @ApiResponse(code = 401,message = "权限不足{id =1}",response = User.class),
    })
    public ResponseEntity<User> add(User user){
        if(user.getId() == 1){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);// 400
        }else if (user.getId() == 2){
            return new ResponseEntity<>(user,HttpStatus.UNAUTHORIZED); // 401
        }else {
            return  ResponseEntity.ok(user);
        }
    }

    @PutMapping
    public ResponseEntity<User> update(User user){
        if(user.getId() == 1){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);// 400
        }else if (user.getId() == 2){
            return new ResponseEntity<>(user,HttpStatus.UNAUTHORIZED); // 401
        }else {
            return  ResponseEntity.ok(user);
        }
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户",notes = "删除返回当前id")
    @ApiResponses({@ApiResponse(code = 200,message = "返回成功",response = User.class),
            @ApiResponse(code = 400,message = "参数没有填好{id =1}",response = User.class),
            @ApiResponse(code = 401,message = "权限不足{id =1}",response = User.class),
    })
    @ApiImplicitParam(paramType = "path",name = "id",value = "用户主键ID",required = true)
    public ResponseEntity<Integer> delete(@PathVariable int id){
        if(id == 1){
            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);// 400
        }else if (id == 2){
            return new ResponseEntity<>(id,HttpStatus.UNAUTHORIZED); // 401
        }else {
            return  ResponseEntity.ok(id);
        }
    }
    @GetMapping("/{id}")
    @ApiIgnore
    public ResponseEntity<Integer> toUpdate(@PathVariable int id){
        if(id == 1){
            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);// 400
        }else if (id == 2){
            return new ResponseEntity<>(id,HttpStatus.UNAUTHORIZED); // 401
        }else {
            return  ResponseEntity.ok(id);
        }
    }

    @PostMapping("/list-page")
    @ApiOperation(value = "分页查询",notes = "得到分页对象pageInfo")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "pageNum",value = "当前页",required = false,defaultValue = "1"),
                         @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页行数",required = false,defaultValue = "10")
    })
    public ResponseEntity<String> findByPage(@RequestParam(defaultValue = "1",required = false) int pageNum,@RequestParam(defaultValue = "10",required = false) int pageSize){
       return ResponseEntity.ok("find page ......");
    }

}
