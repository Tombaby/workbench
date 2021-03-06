<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
<link rel="stylesheet" href="${assets}/jquery-ui-1.11.4.custom/jquery-ui.min.css"/>
<link rel="stylesheet" href="${assets}/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css"/>
<link rel="stylesheet" href="${assets}/jquery-ui-1.11.4.custom/jquery-ui.structure.min.css"/>
<link rel="stylesheet" href="${assets}/jqgrid-4.8.2/css/ui.jqgrid.css"/>
<link rel="stylesheet" href="${assets}/jstree/themes/default/style.min.css" />
<link rel="stylesheet" href="${assets}/mine/css/my.css" />
<style>
.tree-toolbar {
    height: 30px;
}

.tree-panel {
    min-height: 700px;
    margin: 1px;
    border-right: 1px solid #ccc;
}

.dept-panel {
    margin: 1px;
    padding: 5px;
    border: 1px solid #999;
    background-color: #ccc;
    overflow: auto;
}
.user-panel {
    margin: 1px;
    padding: 5px;
    border: 1px solid #999;
    background-color: #ccc;
    overflow: auto;
}
.grid-panel {
    margin: 1px;
    padding: 5px;
    border: 1px solid #999;
    background-color: #ccc;
    overflow: auto;
}
</style>
</head>
<body>
<div class="headbar">

</div>
<div class="mainbox">
    <div class="sidebar">

    </div>
    <div class="main">
        <div class="user-row">
            <div class="user-col2">                
                <div class="tree-panel">
                    <div class="tree-toolbar">
                    
                    </div>
                    <div id="treeview">

                    </div>
                </div>
            </div>
            <div class="user-col8">
                <div id="data">
                    <div class="dept-panel">
                        <form id="dept-frm">
                            <table>
                            <tr><td colspan="8"><strong>Department Info:</strong></td></tr>
                            <tr>
                                <td><label>Dept ID:</label></td><td><input name="deptId" type="text"></input></td>
                                <td><label>Name:</label></td><td><input name="name" type="text"></input></td>
                                <td><label>Parent ID:</label></td><td><input name="parentId" type="text"></input></td>
                                <td><label>Notes:</label></td><td><input name="notes" type="text"></input></td>
                            </tr>
                            <tr>
                                <td><label>Leader ID:</label></td><td><input name="leaderId" type="text"></input></td>
                                <td><label>Location:</label></td><td><input name="location" type="text"></input></td>
                                <td><label>Address:</label></td><td><input name="address" type="text"></input></td>
                                <td><label>Tel:</label></td><td><input name="tel" type="text"></input></td>
                            </tr>
                            <tr>
                                <td><label>Fax:</label></td><td><input name="fax" type="text"></input></td>
                                <td><label>Post Code:</label></td><td><input name="postCode" type="text"></input></td>
                            </table>
                            </tr>
                        </form>
                    </div>
                    <!--
                    <hr />
                    <div class="user-panel">
                        <form id="user-frm">
                            <table>
                            <tr><td colspan="8"><strong>User Info:</strong></td></tr>
                            <tr>
                                <td><label>User ID:</label></td><td><input name="userId" type="text"></input></td>
                                <td><label>User Name:</label></td><td><input name="userName" type="text"></input></td>
                                <td><label>Password:</label></td><td><input name="password" type="text"></input></td>
                                <td><label>Real Name:</label></td><td><input name="realName" type="text"></input></td>
                            </tr>
                            <tr>
                                <td><label>Email:</label></td><td><input name="email" type="text"></input></td>
                                <td><label>Dept ID:</label></td><td><input name="deptId" type="text"></input></td>
                            </tr>
                            </table>
                        </form>
                    </div>
                    -->
                    <hr />
                    <div class="grid-panel">
                        <table id="datagrid"></table>
                        <div id="datagrid-bar"></div>
                    </div>
                </div>        
            </div>
        </div>
    </div>
</div>
<div class="footbar">
</div>  
<script src="${assets}/jquery-1.11.3/jquery-1.11.3.min.js"></script>
<script src="${assets}/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<script src="${assets}/jqgrid-4.8.2/js/i18n/grid.locale-en.js"></script>
<script src="${assets}/jqgrid-4.8.2/js/jquery.jqGrid.min.js"></script>
<script src="${assets}/jstree/jstree.min.js"></script>
<script>



function buildTreeNodes(treenodes) {
    for (var i=0; i<treenodes.length; i++){
        treenodes[i].id=treenodes[i].deptId;
        treenodes[i].text=treenodes[i].name;
        buildTreeNodes(treenodes[i].children);
    }
}

function getDeptInfoByDeptId(treenodes, id) {
    var result = undefined;
    for(var i=0; i<treenodes.length; i++){
        if(treenodes[i].id==id){
            result = treenodes[i];
            break;
        } 
        result = getDeptInfoByDeptId(treenodes[i].children, id);
        if (result != undefined)
            break;
    }
    return result;
}

function fillDeptInfoPanel(data) {
    $('#dept-frm input[name="deptId"]').val(data['deptId']);
    $('#dept-frm input[name="name"]').val(data['name']);
    $('#dept-frm input[name="parentId"]').val(data['parentId']);
    $('#dept-frm input[name="notes"]').val(data['notes']);
    $('#dept-frm input[name="leaderId"]').val(data['leaderId']);
    $('#dept-frm input[name="location"]').val(data['location']);
    $('#dept-frm input[name="address"]').val(data['address']);
    $('#dept-frm input[name="tel"]').val(data['tel']);
    $('#dept-frm input[name="fax"]').val(data['fax']);
    $('#dept-frm input[name="postCode"]').val(data['postCode']);
}

function fillDataGrid(data){
    if (data == null)
        return;
    $("#datagrid").jqGrid("clearGridData");
    for(var i=0;i<=data.length;i++)
        $("#datagrid").jqGrid('addRowData',i+1,data[i]);
    $("#grid_id").setGridParam({rowNum:data.length}).trigger("reloadGrid");
}

var treenodes = [${result}];
buildTreeNodes(treenodes);
$('#treeview').jstree({
    'core' : {
        'data' : treenodes
    }
}).on('select_node.jstree', function(e,data){
    var deptid = data.node.id;
    console.log(deptid);
    var deptInfo = getDeptInfoByDeptId(treenodes, deptid);
    if(deptInfo==undefined) {
        return false;
    }
    var users = deptInfo['users'];
    console.log(users);
    fillDeptInfoPanel(deptInfo);
    fillDataGrid(users);

});

var lastsel = null;
$("#datagrid").jqGrid({
    datatype: "local",
    height: 300,
    autowidth: true,
    colNames:['UserID','Name', 'RealName', 'Email','Password', 'DepartmentID'],
    colModel:[
        {name:'userId',index:'userId', width:100, sorttype:"int", editable:false },
        {name:'name',index:'name', width:120, editable:true, edittype:'text', editrules:{number:false}},
        {name:'realName',index:'realName', width:150, editable:true, edittype:'text', editrules:{required:true}, editoptions:{axlength:30}},
        {name:'email',index:'email', width:150, editable:true, edittype:'text', editrules:{required:true, email:true}, editoptions:{axlength:120}},
        {name:'password',index:'password', width:120, editable:true, edittype:'text', editrules:{required:true}},
        {name:'deptId',index:'deptId', width:120, editable:false}
    ],
    toolbar: [true,"top"],
    multiselect: false,
    pager: '#datagrid-bar',
    caption: "User List",
    cellEdit: true,
    cellsubmit: 'clientArray',
    afterEditCell: function (id,name,val,iRow,iCol){
		if(name=='password') {
			jQuery("#"+iRow+"_password","#datagrid").datepicker({dateFormat:"yy-mm-dd"});
		}
	},
    afterSaveCell : function(id,name,val,iRow,iCol) {
        //alert(id);
        var row = $('#datagrid').jqGrid('getLocalRow',id);
        alert(row['name']);
    }
});
$("#datagrid").jqGrid('navGrid', '#datagrid-bar', {edit:false, del:false, add:false});
$("#t_datagrid").append('<button id="tbBtnAdd" class="ui-button ui-widget ui-state-default ui-button-text-icon-primary" role="button"><span class="ui-button-icon-primary ui-icon ui-icon-locked"></span><span class="ui-button-text">Add</span></button><button id="tbBtnDelete" class="ui-button ui-widget ui-state-default ui-button-text-icon-primary" role="button"><span class="ui-button-icon-primary ui-icon ui-icon-locked"></span><span class="ui-button-text">Delete</span></button><button id="tbBtnSave" class="ui-button ui-widget ui-state-default ui-button-text-icon-primary" role="button"><span class="ui-button-icon-primary ui-icon ui-icon-locked"></span><span class="ui-button-text">Save</span></button>');
$("#tbBtnAdd","#t_datagrid").click(function(){
	var ids = $("#datagrid").jqGrid('getDataIDs');
    var rowid = (ids.length ==0 ? 1: Math.max.apply(Math,ids)+1);
    var newrow = {userId:-1, name:"user", realName:"user", email:"xxx@xxx.com", password:"123", deptId:$('#dept-frm input[name="deptId"]').val()};
    $('#datagrid').jqGrid('addRowData', rowid, newrow);
    var deptInfo = getDeptInfoByDeptId(treenodes, newrow.deptId);
    if(deptInfo==undefined) {
        return false;
    }
    deptInfo['users'].push(newrow);
});

$("#tbBtnDelete","#t_datagrid").click(function(){
	var id = $('#datagrid').jqGrid('getGridParam', 'selrow');
    var row = $('#datagrid').jqGrid('getLocalRow',id);
    $('#datagrid').jqGrid('delRowData', id);
    var deptInfo = getDeptInfoByDeptId(treenodes, row.deptId);
    if(deptInfo==undefined) {
        return false;
    }
    for(var i = 0; i<deptInfo['users'].length; i++) {
        if(deptInfo['users'][i].userId == row.userId) {
            deptInfo['users'].splice(i, 1);
            break;
        }
    }
});


</script>
</body>
</html>