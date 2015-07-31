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
                            <label>Dept ID:</label>
                            <input name="deptId" type="text"></input>
                            <label>Name:</label>
                            <input name="name" type="text"></input>
                            <label>Parent ID:</label>
                            <input name="parentId" type="text"></input>
                            <label>Notes:</label>
                            <input name="notes" type="text"></input>
                            <label>Leader ID:</label>
                            <input name="leaderId" type="text"></input>
                            <label>Location:</label>
                            <input name="location" type="text"></input>
                            <label>Address:</label>
                            <input name="address" type="text"></input>
                            <label>Tel:</label>
                            <input name="tel" type="text"></input>
                            <label>Fax:</label>
                            <input name="fax" type="text"></input>
                            <label>Post Code:</label>
                            <input name="postCode" type="text"></input>
                        </form>
                    </div>
                    <hr />
                    <div class="user-panel">
                        <form id="user-frm">
                        
                        </form>
                    </div>
                    <hr />
                    <table id="datagrid"></table>
                    <div id="datagrid-bar"></div>
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

function getUsersByDeptId(treenodes,id){
    var result = undefined;
    for(var i=0; i<treenodes.length; i++){
        if(treenodes[i].id==id){
            result = treenodes[i].users;
            break;
        } 
        result = getUsersByDeptId(treenodes[i].children, id);
        if (result != undefined)
            break;
    }
    return result;
}

function fillDataGrid(data){
    if (data == null)
        return;
    $("#datagrid").jqGrid("clearGridData");
    for(var i=0;i<=data.length;i++)
        $("#datagrid").jqGrid('addRowData',i+1,data[i]);
    $("#grid_id").setGridParam({rowNum:data.length}).trigger("reloadGrid");
}

$("#datagrid").jqGrid({
    datatype: "local",
    height: 300,
    autowidth: true,
    colNames:['UserID','Name', 'RealName', 'Email','Password'],
    colModel:[
        {name:'userId',index:'userId', width:100, sorttype:"int"},
        {name:'name',index:'name', width:120},
        {name:'realName',index:'realName', width:150},
        {name:'email',index:'email', width:150},
        {name:'password',index:'password', width:120}
    ],
    multiselect: false,
    pager: '#datagrid-bar',
    caption: "User List"
});
$("#datagrid").jqGrid('navGrid', '#datagrid-bar', {});


var treenodes = [${result}];
buildTreeNodes(treenodes);
$('#treeview').jstree({
    'core' : {
        'data' : treenodes
    }
}).on('select_node.jstree', function(e,data){
    var deptid = data.node.id;
    console.log(deptid);
    var users = getUsersByDeptId(treenodes, deptid);
    console.log(users);    
    fillDataGrid(users);

});



</script>
</body>
</html>