$(document).ready(function(){
	$('#logOut').click(function(){
		
		$.ajax({
				
        type: "POST",
        data:   {   userLogout: 'LOGOUT', 
        		},
        url: "logoutAuthServlet",

        beforeSend: $.proxy(function(data) {
        	           
        }, this),
        success: function(responseJson){
        	if (responseJson.redirect) {
    	        window.location = responseJson.redirect;
    	        return;
    	    }
        	
            }
    	});
     
    });
});