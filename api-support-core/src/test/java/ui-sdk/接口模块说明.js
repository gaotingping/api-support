/**
 * 将每个服务的接口调用，抽取为公共的数据访问
 * 以后DOM的创建于接口调用相互分离，数据访问
 * 抽取为UIDA，按照如下层次结构定义:
 * 相关代码由后台接口统一生成
 */

/**
 * 抽取UIDA
 */
var UIDA={};

/**
 * 一个项目可能访问多个服务
 * 多个服务抽取一层，如:
 * 西医结业模板
 */
UIDA.GMS={};

/**
 * 数据访问前置处理器，你可以
 * 在这里进行token等公共参数的填充
 * 以及“loading...”的效果处理
 */
UIDA.GMS.preHandler=function(req){
	req.token="token";
	req.args.token="token";
};

/**
 * 数据访问后置处理器，你可以对返回结果
 * 做统一处理等(埋点，收集日志等)
 */
UIDA.GMS.postHandler=function(req,data){
	
};

/**
 *  编辑流程【定义】
 *  @param  xxx[FIXME:能自动生成，但是]
 *  @return xxx
 */
UIDA.GMS.flowEdit = function(args,callBack(data){
	
	var req={
		//"token":xxx,//全局token获取,在前置处理器中获取
		"service":"",//自动生成
		"group":"",//自动生成
		"action":"" //自动生成
		"args":args
	};
	
	//前置处理器
	UIDA.GMS.preHandler(req);
	
	commonLogic.serviceCaller(req,function(data){
		
		//后置处理器
		UIDA.GMS.preHandler(data);
		
	    if(data.flag == "true"){
	        callBack(data.result);
	    }else{
	    	//统一错误处理
	        Elf.components.toast({text:data.error});
	    }
	});
});

/**
  各业务调用形式,只需要关注各
  业务的args参数部分即可，如:
  编辑流程调用
*/
UIDA.GMS.flowEdit({
	   //仅仅args中的东西
	},function(data){
		//业务逻辑
	}
});