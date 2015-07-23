<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome!</title>
    <link rel="stylesheet" href="${assets}/jstree/themes/default/style.min.css" />
</head>
<body>
    <div id="headbar">

    </div>
    <div id="sidebar">

    </div>
    <div id="main">
        <div id="left">
            <div id="treeview">

            </div>    
        </div>
        <div id="middle">
        
        </div>
    </div>
    <div id="footbar">
    </div>  
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="${assets}/jstree/jstree.min.js"></script>
<script>
var treenodes = [{ 
    "id": 1, 
    "text":"node1", 
    "state" : { "opened" : true },
    "children": [
        {"id":11, "text":"node11"},
        {"id":12, "text":"node12"},
        {"id":13, "text":"node13"}
    ]
},
{ 
    "id": 2, 
    "text":"node2", 
    "state" : { "opened" : true },
    "children":[
        {"id":21, "text":"node21"},
        {"id":22, "text":"node22"},
        {"id":23, "text":"node23"}
    ]        
}];

$('#treeview').jstree({
    'core' : {
        'data' : treenodes
    }
}).on('select_node.jstree', function(e,data){
    
    /*
    if(data.node.state.opened){
        $('#treeview').jstree('close_node', data.node.id);
    } else {
        $('#treeview').jstree('open_node', data.node.id);
    }
    */
});
</script>
</body>
</html>