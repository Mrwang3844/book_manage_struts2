package cn.yoylee.action.test;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage(value = "struts-default")
@Namespace(value = "/test")
public class HelloAction  extends ActionSupport{


    @Action(
            value = "helloWorld",
            results = {
                    @Result(name ="success",location = "/Success.jsp",type = "dispatcher"),
                    @Result(name ="fail",location = "/Fail.jsp",type = "dispatcher")
            }
    )
    public String test(){
        String forward;
        forward = "success";
        return forward;
    }


}
