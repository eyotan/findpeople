$(document).ready(function(){
	
	$(function(){
		var user = Cookies.get("userLoginUnbox");
		
		if(user){
			document.getElementById('loggedIn').value = user
			$('#loggedIn').text(user);
		}
	});
	
	$('#searchUser').click(function(){
		
		$.ajax({
				
        type: "POST",
        data:   {   surName: $('#surName').val(), 
        			userName1: $('#userName1').val(),
        			userName2: $('#userName2').val(),
        			cityName: $('#cityName').val(),
        			autoName: $('#autoName').val(),
                },
        url: "findPeopleServlet",

        beforeSend: $.proxy(function(data) {
        	var sendFio = false;
            $('form#searchFormFio input').each(function(){
                if($(this).val() && $(this).val() != ''){
                	$(this).removeAttr('style');
                    sendFio = true;
                }else{
                	$(this).css('border-color','yellow');
                }
            });
                    
            if(!sendFio){
            	alert("Незаполнены поля");
            	return false;
            } 
            	
           
        }, this),
        success: function(responseJson){
        	if (responseJson.redirect) {
    	        window.location = responseJson.redirect;
    	        return;
    	    }else{
    	    	$("#resultSearchTable").find("tr:gt(0)").remove();
    	    	var tableTemp = $("#resultSearchTable");
    	    	$.each(responseJson, function(key,value) { 
                    var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                       rowNew.children().eq(0).text(value['surName']); 
                       rowNew.children().eq(1).text(value['userName1']); 
                       rowNew.children().eq(2).text(value['userName2']); 
                       rowNew.children().eq(3).text(value['cityName']); 
                       rowNew.children().eq(4).text(value['autoName']); 
                       rowNew.appendTo(tableTemp);
               });
    	    	$("#resultSearch").show();
    	    }
        	
            }
    	});
     
    });
});