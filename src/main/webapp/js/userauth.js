$(document).ready(function(){
	
	$(function(){
		var login = Cookies.get("userLogin");
		var pass = Cookies.get("userPassword");
		if(login && login != ''){
			document.getElementById('userLogin').value = login
		}
		if(pass && pass != ''){
			document.getElementById('userPassword').value = pass
		}
	});
	
	$('#enterUser').click(function(){
   
		$.ajax({
				
        type: "POST",
        data:   {   userLogin: $('#userLogin').val(), 
        			userPassword: $('#userPassword').val(),
        			userCheckbox: $('#userCheckbox').prop('checked'),
                },
        url: "loginAuthServlet",

        beforeSend: $.proxy(function(data) {
        	var  send = true;
            $('form#loginForm input').each(function(){
                if(!$(this).val() || $(this).val() == ''){
                   $(this).css('border-color','red');
                   send = false;
                }else{
                	$(this).removeAttr('style');
                }
            });
                    
            if(!send) return false;
           
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