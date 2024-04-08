package cupcake.constants;

public interface Pages
{     //Alle vores websides sider, inklusiv 'send' sider, hvis du kan se det I browseren oppe i URL, så er det en side og skal være her :)
    
    String INDEX_GET_PAGE = "/";
    String INDEX_POST_PAGE = INDEX_GET_PAGE + FormParam.cupcakeSend;
    
    String LOGIN_GET_PAGE = "/login";
    String LOGIN_POST_PAGE = LOGIN_GET_PAGE;
    
    String CREATE_ACCOUNT_GET_PAGE = "/create-account";
    String CREATE_ACCOUNT_POST_PAGE = CREATE_ACCOUNT_GET_PAGE;
    
    String CART_GET_PAGE = "/cart";
    String CART_REMOVE_PAGE = CART_GET_PAGE + "/remove";
    String CART_BUY_PAGE = CART_GET_PAGE + "/buy";
    
    String ORDER_HISTORY_GET_PAGE = "/order-history";
    String ORDER_HISTORY_POST_PAGE = ORDER_HISTORY_GET_PAGE;
    String ORDER_HISTORY_DONE_PAGE = ORDER_HISTORY_GET_PAGE + "/done";
    String ORDER_HISTORY_REMOVE_PAGE = ORDER_HISTORY_GET_PAGE + "/remove";
    
    
    String LOGOUT_POST_PAGE = "/logout";
    
    String ORDER_HISTORY_SORT_PAGE = ORDER_HISTORY_GET_PAGE+"/admin-sort";
    
}
    
    

