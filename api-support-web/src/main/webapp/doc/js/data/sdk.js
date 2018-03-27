/*SDK文档 */
var UIDA={};

UIDA.GMS={};

/**
 * 前置处理器
 * 你可以在这里加一些公关业务逻辑
 * 如进行token等公共参数的填充
 * @param req 请求
 * @returns 仅返回true时继续执行,否则“return”
 */
UIDA.GMS.preHandler=function(req){
	req.serviceModule="serviceModule";
	req.token="token";
	return true;
};

/**
 * 后置处理器
 * 你可以对整个请求响应做统一的业务处理
 * 如统一的错误错误提示
* @param req  请求参数
* @param data 响应结果
* @returns 仅返回true时继续执行,否则“return”
 */
UIDA.GMS.postHandler=function(req,data){
	return true;
};

/**
 * 发送和统一处理响应
 * @param req 请求参数
 * @param callBack
 */
UIDA.GMS.sendReq=function(req,callBack){
	
	//前置处理器
	var f=UIDA.GMS.preHandler(req);
	if(f != true){
		return;
	}
	
	commonLogic.serviceCaller(req,function(data){
		//后置处理器
		var f=UIDA.GMS.preHandler(data);
		if(f == true && data.flag == "true"){
			callBack(data.result);
		}
	});
};

/**
 * 请假申请-编辑
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.editApply = function(args,callBack){
	var req={
		"serviceNumber":"001001001",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 请假申请-按ID查询
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.findApplyById = function(args,callBack){
	var req={
		"serviceNumber":"001001002",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 请假申请-查询列表
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.queryApply = function(args,callBack){
	var req={
		"serviceNumber":"001001003",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 请假申请-按id删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delApplyById = function(args,callBack){
	var req={
		"serviceNumber":"001001004",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 请假申请-按ids删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delApplyByIds = function(args,callBack){
	var req={
		"serviceNumber":"001001005",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核步骤-编辑
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.editApplyAuditStep = function(args,callBack){
	var req={
		"serviceNumber":"001002001",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核步骤-按ID查询
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.findApplyAuditStepById = function(args,callBack){
	var req={
		"serviceNumber":"001002002",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核步骤-查询列表
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.queryApplyAuditStep = function(args,callBack){
	var req={
		"serviceNumber":"001002003",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核步骤-按id删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delApplyAuditStepById = function(args,callBack){
	var req={
		"serviceNumber":"001002004",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核步骤-按ids删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delApplyAuditStepByIds = function(args,callBack){
	var req={
		"serviceNumber":"001002005",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核各步骤审核人-按ID查询
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.findApplyAuditStepMemberById = function(args,callBack){
	var req={
		"serviceNumber":"001003002",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核各步骤审核人-编辑
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.editApplyAuditStepMember = function(args,callBack){
	var req={
		"serviceNumber":"001003001",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核各步骤审核人-查询列表
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.queryApplyAuditStepMember = function(args,callBack){
	var req={
		"serviceNumber":"001003003",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核各步骤审核人-按id删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delApplyAuditStepMemberById = function(args,callBack){
	var req={
		"serviceNumber":"001003004",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 申请审核各步骤审核人-按ids删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delApplyAuditStepMemberByIds = function(args,callBack){
	var req={
		"serviceNumber":"001003005",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程-编辑
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.editFlow = function(args,callBack){
	var req={
		"serviceNumber":"001004001",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程-按ID查询
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.findFlowById = function(args,callBack){
	var req={
		"serviceNumber":"001004002",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程-查询列表
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.queryFlow = function(args,callBack){
	var req={
		"serviceNumber":"001004003",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程-按id删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delFlowById = function(args,callBack){
	var req={
		"serviceNumber":"001004004",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程-按ids删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delFlowByIds = function(args,callBack){
	var req={
		"serviceNumber":"001004005",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程路由-按ID查询
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.findFlowRouteById = function(args,callBack){
	var req={
		"serviceNumber":"001005002",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程路由-查询列表
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.queryFlowRoute = function(args,callBack){
	var req={
		"serviceNumber":"001005003",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程路由-按id删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delFlowRouteById = function(args,callBack){
	var req={
		"serviceNumber":"001005004",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程路由-按ids删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delFlowRouteByIds = function(args,callBack){
	var req={
		"serviceNumber":"001005005",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程路由-编辑
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.editFlowRoute = function(args,callBack){
	var req={
		"serviceNumber":"001005001",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程步骤-编辑
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.editFlowRouteStep = function(args,callBack){
	var req={
		"serviceNumber":"001006001",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程步骤-按ID查询
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.findFlowRouteStepById = function(args,callBack){
	var req={
		"serviceNumber":"001006002",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程步骤-查询列表
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.queryFlowRouteStep = function(args,callBack){
	var req={
		"serviceNumber":"001006003",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程步骤-按id删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delFlowRouteStepById = function(args,callBack){
	var req={
		"serviceNumber":"001006004",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

/**
 * 流程步骤-按ids删除
 * @param  args
 * @param  callBack
 * @return
 */
UIDA.GMS.delFlowRouteStepByIds = function(args,callBack){
	var req={
		"serviceNumber":"001006005",
		"args":args
	};
	UIDA.GMS.sendReq(req,callBack);
};

