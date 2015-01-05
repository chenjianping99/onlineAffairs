package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ServiceItem
 * @Description: 服务事项
 * @author chenjianping
 * @date 2014-11-14
 * 
 */
public class ServiceItem extends BaseBean {

    private static final long serialVersionUID = 6581308161357116470L;

    /**
     * 事项编码。标识属性
     */
    private String service_code;
    
    /**
     * 事项名称
     */
    private String name;
    
    /**
     * 事项类型(代码值)，参照 ServiceItemType 代码。
     */
    private String service_item_type;

    /**
     * 事权级别(代码值)，参照 Authority_Level 代码
     */
    private String authority_level;
    
    /**
     * 事项状态(代码值)，参照ServiceItemStatus代码
     */
    private String status;
    
    /**
     * 事项版本
     */
    private String version;
    
    /**
     * 受理主题(受理机构)
     */
    private String service_agent;
    
    /**
     * 办理主体类型(代码值)
     */
    private String service_agent_type;
    
    /**
     * 办理对象
     */
    private String service_object;
    
    /**
     * 办理对象类型(代码值)
     */
    private String service_object_type;

    /**
     * 办理依据
     */
    private String legal_basis;
    
    /**
     * 办理条件
     */
    private String conditions;
    
    /**
     * 收费依据和标准
     */
    private String charge;
    
    /**
     * 法定期限
     */
    private String legal_period;
    
    /**
     * 承诺期限
     */
    private String promised_period;
    
    /**
     * 提交材料
     */
    private String submit_documents;
    
    /**
     * 业务表格
     */
    private String forms;
    
    /**
     * 窗口办理流程
     */
    private String window_process;
    
    /**
     * 网上办理流程
     */
    private String online_process;
    
    /**
     * 权力运行流程
     */
    private String power_process;
    
    /**
     * 监督电话
     */
    private String complaint_phone;
    
    /**
     * 常见问题
     */
    private String faq;

    /**
     * 在线申办服务网址
     */
    private String online_service_url;
    
    /**
     * 业务咨询服务网址
     */
    private String consult_service_url;
    
    /**
     * 结果反馈服务网址
     */
    private String result_query_url;
    
    /**
     * 进度查询服务网址
     */
    private String progress_query_url;
    
    /**
     * 是否网上缴费
     */
    private String pay_online;
    
    /**
     * 是否需要身份验证
     */
    private String authentication;
    
    /**
     * 是否网上全程办结
     */
    private String online_done;
    
    /**
     * 是否投资审批事项
     */
    private String invest;
    
    /**
     * 是否涉外事项
     */
    private String foreign;
    
    /**
     * 提供办事指南服务情况(代码值)，参照 ProvideServiceType代码
     */
    private String provide_guide;
    
    /**
     * 提供表格下载服务情况(代码值)，参照 ProvideServiceType代码值
     */
    private String provide_forms;
    
    /**
     * 提供网上咨询服务情况(代码值)，参照 ProvideServiceType代码值
     */
    private String provide_consult;
    
    /**
     * 提供在线申办服务情况(代码值)，参照 ProvideServiceType代码
     */
    private String provide_apply;
    
    /**
     * 提供结果反馈服务情况(代码值)，参照 ProvideServiceType代码
     */
    private String provide_result;
    
    /**
     * 提供进度查询服务情况(代码值)，参照 ProvideServiceType代码
     */
    private String provide_process;

    /**
     * 提供星级评价服务情况(代码值)，参照 ProvideServiceType代码
     */
    private String provide_rate;
    
    /**
     * 所属服务分类的代码列表，多个分类代码有逗号分隔开
     */
    private String service_catalogs;
    
    /**
     * 到现场次数(代码值)，参照 TransportCount 代码
     */
    private String transport_count;
    
    /**
     * 办事时间。正常办理需要的时间周期，单位为工作日(不包含法定节假日)
     */
    private String time_cost;
    
    /**
     * 申报网上办事深度(代码值)。参照 ServiceLevel代码。
     */
    private String declare_service_level;
    
    /**
     * 核准网上办事深度(代码值)。参照 ServiceLevel 代码。
     */
    private String approve_service_level;
    
    /**
     * 核准时间
     */
    private String approve_time;
    
    /**
     * 顺序号。用于排序
     */
    private String sort_order;
    
    /**
     * 创建人
     */
    private String creator;
    
    /**
     * 创建时间
     */
    private String creation_time;
    
    /**
     * 最后修改人
     */
    private String last_modificator;
    
    /**
     * 最后修改时间
     */
    private String last_modification_time;

    
    public String getService_code()
    {
        return service_code;
    }

    public void setService_code(String service_code)
    {
        this.service_code = service_code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getService_item_type()
    {
        return service_item_type;
    }

    public void setService_item_type(String service_item_type)
    {
        this.service_item_type = service_item_type;
    }

    public String getAuthority_level()
    {
        return authority_level;
    }

    public void setAuthority_level(String authority_level)
    {
        this.authority_level = authority_level;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getService_agent()
    {
        return service_agent;
    }

    public void setService_agent(String service_agent)
    {
        this.service_agent = service_agent;
    }

    public String getService_agent_type()
    {
        return service_agent_type;
    }

    public void setService_agent_type(String service_agent_type)
    {
        this.service_agent_type = service_agent_type;
    }

    public String getService_object()
    {
        return service_object;
    }

    public void setService_object(String service_object)
    {
        this.service_object = service_object;
    }

    public String getService_object_type()
    {
        return service_object_type;
    }

    public void setService_object_type(String service_object_type)
    {
        this.service_object_type = service_object_type;
    }

    public String getLegal_basis()
    {
        return legal_basis;
    }

    public void setLegal_basis(String legal_basis)
    {
        this.legal_basis = legal_basis;
    }

    public String getConditions()
    {
        return conditions;
    }

    public void setConditions(String conditions)
    {
        this.conditions = conditions;
    }

    public String getCharge()
    {
        return charge;
    }

    public void setCharge(String charge)
    {
        this.charge = charge;
    }

    public String getLegal_period()
    {
        return legal_period;
    }

    public void setLegal_period(String legal_period)
    {
        this.legal_period = legal_period;
    }

    public String getPromised_period()
    {
        return promised_period;
    }

    public void setPromised_period(String promised_period)
    {
        this.promised_period = promised_period;
    }

    public String getSubmit_documents()
    {
        return submit_documents;
    }

    public void setSubmit_documents(String submit_documents)
    {
        this.submit_documents = submit_documents;
    }

    public String getForms()
    {
        return forms;
    }

    public void setForms(String forms)
    {
        this.forms = forms;
    }

    public String getWindow_process()
    {
        return window_process;
    }

    public void setWindow_process(String window_process)
    {
        this.window_process = window_process;
    }

    public String getOnline_process()
    {
        return online_process;
    }

    public void setOnline_process(String online_process)
    {
        this.online_process = online_process;
    }

    public String getPower_process()
    {
        return power_process;
    }

    public void setPower_process(String power_process)
    {
        this.power_process = power_process;
    }

    public String getComplaint_phone()
    {
        return complaint_phone;
    }

    public void setComplaint_phone(String complaint_phone)
    {
        this.complaint_phone = complaint_phone;
    }

    public String getFaq()
    {
        return faq;
    }

    public void setFaq(String faq)
    {
        this.faq = faq;
    }

    public String getOnline_service_url()
    {
        return online_service_url;
    }

    public void setOnline_service_url(String online_service_url)
    {
        this.online_service_url = online_service_url;
    }

    public String getConsult_service_url()
    {
        return consult_service_url;
    }

    public void setConsult_service_url(String consult_service_url)
    {
        this.consult_service_url = consult_service_url;
    }

    public String getResult_query_url()
    {
        return result_query_url;
    }

    public void setResult_query_url(String result_query_url)
    {
        this.result_query_url = result_query_url;
    }

    public String getProgress_query_url()
    {
        return progress_query_url;
    }

    public void setProgress_query_url(String progress_query_url)
    {
        this.progress_query_url = progress_query_url;
    }

    public String getPay_online()
    {
        return pay_online;
    }

    public void setPay_online(String pay_online)
    {
        this.pay_online = pay_online;
    }

    public String getAuthentication()
    {
        return authentication;
    }

    public void setAuthentication(String authentication)
    {
        this.authentication = authentication;
    }

    public String getOnline_done()
    {
        return online_done;
    }

    public void setOnline_done(String online_done)
    {
        this.online_done = online_done;
    }

    public String getInvest()
    {
        return invest;
    }

    public void setInvest(String invest)
    {
        this.invest = invest;
    }

    public String getForeign()
    {
        return foreign;
    }

    public void setForeign(String foreign)
    {
        this.foreign = foreign;
    }

    public String getProvide_guide()
    {
        return provide_guide;
    }

    public void setProvide_guide(String provide_guide)
    {
        this.provide_guide = provide_guide;
    }

    public String getProvide_forms()
    {
        return provide_forms;
    }

    public void setProvide_forms(String provide_forms)
    {
        this.provide_forms = provide_forms;
    }

    public String getProvide_consult()
    {
        return provide_consult;
    }

    public void setProvide_consult(String provide_consult)
    {
        this.provide_consult = provide_consult;
    }

    public String getProvide_apply()
    {
        return provide_apply;
    }

    public void setProvide_apply(String provide_apply)
    {
        this.provide_apply = provide_apply;
    }

    public String getProvide_result()
    {
        return provide_result;
    }

    public void setProvide_result(String provide_result)
    {
        this.provide_result = provide_result;
    }

    public String getProvide_process()
    {
        return provide_process;
    }

    public void setProvide_process(String provide_process)
    {
        this.provide_process = provide_process;
    }

    public String getProvide_rate()
    {
        return provide_rate;
    }

    public void setProvide_rate(String provide_rate)
    {
        this.provide_rate = provide_rate;
    }

    public String getService_catalogs()
    {
        return service_catalogs;
    }

    public void setService_catalogs(String service_catalogs)
    {
        this.service_catalogs = service_catalogs;
    }

    public String getTransport_count()
    {
        return transport_count;
    }

    public void setTransport_count(String transport_count)
    {
        this.transport_count = transport_count;
    }

    public String getTime_cost()
    {
        return time_cost;
    }

    public void setTime_cost(String time_cost)
    {
        this.time_cost = time_cost;
    }

    public String getDeclare_service_level()
    {
        return declare_service_level;
    }

    public void setDeclare_service_level(String declare_service_level)
    {
        this.declare_service_level = declare_service_level;
    }

    public String getApprove_service_level()
    {
        return approve_service_level;
    }

    public void setApprove_service_level(String approve_service_level)
    {
        this.approve_service_level = approve_service_level;
    }

    public String getApprove_time()
    {
        return approve_time;
    }

    public void setApprove_time(String approve_time)
    {
        this.approve_time = approve_time;
    }

    public String getSort_order()
    {
        return sort_order;
    }

    public void setSort_order(String sort_order)
    {
        this.sort_order = sort_order;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreation_time()
    {
        return creation_time;
    }

    public void setCreation_time(String creation_time)
    {
        this.creation_time = creation_time;
    }

    public String getLast_modificator()
    {
        return last_modificator;
    }

    public void setLast_modificator(String last_modificator)
    {
        this.last_modificator = last_modificator;
    }

    public String getLast_modification_time()
    {
        return last_modification_time;
    }

    public void setLast_modification_time(String last_modification_time)
    {
        this.last_modification_time = last_modification_time;
    }



}
