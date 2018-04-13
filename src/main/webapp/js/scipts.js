function genrateLeftSlidebar(){
	$("ul.list-group").empty();
	
	$.ajax({
		type : "post",
		url : "databaseoperations.do",
		data : "type=getGroupsByConnection",
		success : function(response) {
			jsonObject= JSON.parse(response);
			if(jsonObject.result=="success"){
				var jsonObjectData=jsonObject.data;
				for(itr=0;itr<jsonObjectData.length;itr++)
				{
					genrateSlidebarHTML(jsonObjectData[itr]);
				}
				
				sAddnewConnectionHtml='<li class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span><a href="localconnection.html"> Add New Connection</a></li>';
				$("ul[name='leftSlideBar'].list-group").append(sAddnewConnectionHtml);
			}
			
			
		},
		dataType : 'text'
		});	
}

function genrateSlidebarHTML(eachGroupInformationInJson){
	
	var iConnectionId=eachGroupInformationInJson.connectionId;
	var sConnectionName=eachGroupInformationInJson.connectionName;
	var sConnectionGroupsObj=eachGroupInformationInJson.groups;
	var indexOfConnection=$("ul.list-group li").length;

	
	var sSlideBarHTML='<li class="list-group-item">'+
	'				<div class="group_operation">'+
	'				<span data-toggle="collapse" data-target="#gps_group'+ (indexOfConnection+1) +'" class="glyphicon glyphicon-plus" name="collapse"></span><a href="localconnection.html?connectionid='+ iConnectionId +'"><span class="glyphicon glyphicon-wrench" name="edit-connection"></span></a> '+ sConnectionName +
	'				</div>'+
	'			<div id="gps_group'+ (indexOfConnection+1) +'" class="connection_group collapse">'+
	'					<div class="local_group_name">'+
	'						<span class="glyphicon glyphicon-folder-close"></span> Groups '+
	'					</div>'+
	'<ul name="group-names" class="list-group" style="margin-top: 10px;">';

	if(sConnectionGroupsObj.length>0){
		for(itr=0;itry<sConnectionGroupsObj.length;itr++){
			var sGroupName=sConnectionGroupsObj[itr].groupname;
			sSlideBarHTML+='<li class="list-group-item"><span class="glyphicon glyphicon-wrench"></span><a href="index.html"> '+ sGroupName +'</a></li>';			
		}
	}
	
	sSlideBarHTML+='</ul><div class="local_group_name"><span class="glyphicon glyphicon-plus"></span><a href="connectiongroup.html?connectionid='+ iConnectionId +'">Add New Group</a></div></div></li>';
	$("ul[name='leftSlideBar'].list-group").append(sSlideBarHTML);
}


$(document).on("click",".local_group_name",function(){
	var iconclass=$(this).find("span").attr("class");
	if(iconclass=="glyphicon glyphicon-folder-open"){$(this).find("span").attr("class","glyphicon glyphicon-folder-close");
	$(this).siblings(".list-group").hide();}
	else{$(this).find("span").attr("class","glyphicon glyphicon-folder-open");$(this).siblings(".list-group").show();}
});
//span[name='']
$(document).on("click","span[name='collapse']",function(){
	var iconclass=$(this).attr("class");
	if(iconclass=="glyphicon glyphicon-plus"){
		$(this).attr("class","glyphicon glyphicon-minus");
	}
	else{
		$(this).attr("class","glyphicon glyphicon-plus");
	}
});


function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
