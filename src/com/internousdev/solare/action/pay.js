$(function(){
	var payselect = $("form select.paySelect")
	var cardinfo = $("form select.cardInfo");
	$(cardInfo).attr("disabled","disabled");

	$(pay).change(function(){
		var item = $(this).val();
		if(item == "3"){
			$(cardInfo).removeattr("disabled");
		}else{
			$(cardInfo).attr("disabled","disabled").val("");
		}
	});
});