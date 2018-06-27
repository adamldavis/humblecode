<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${applicationName}: Learn how to code</title>
    <link href="/css/base.css" type="text/css" rel="stylesheet" />
    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="/css/prettify.css" type="text/css" rel="stylesheet" />
    <link href="/css/asciidoctor.css" type="text/css" rel="stylesheet" />

    <script src="/js/jquery-1.11.1.min.js" type="application/javascript"></script>
    <script src="/js/bootstrap.min.js" type="application/javascript"></script>
    <script src="/js/prettify.js" type="application/javascript"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->

    <style>
        .categories { font-size: 2em; }
    </style>

</head>
<body onload="">
 <div class="container">

     <#if name == ""><a href="#" onclick="signUp(); return false">Sign up today!</a></#if>

     <div class="navbar">Hello ${name}!</div>

    <div class="page-header">
        <h1>Welcome to ${applicationName}!</h1>
    </div>

    <article id="categories" class="jumbotron center"></article>

    <script type="application/javascript">
        function loadCategory(name) {

        }
        function getCategories() {
            jQuery.ajax({method: 'get', url: '/api/categories'}).done(
                function(data) {
                    console.log("data=" + data);
                    var list = data;
                    var ul = jQuery('<ul class="categories btn-group"></ul>');
                    list.forEach((cat) => {
                        ul.append('<li class="btn-link" onclick="loadCategory(' + cat.name + ')">'
                            + cat.name+': <i>'+cat.description + '</i></li>')
                    });
                    jQuery('#categories').html(ul);
                }
            ).fail(
                (err) => { alert("Sorry, there was a problem. " + err); console.log(err);}
            );
        }
        jQuery(document).ready(getCategories);
    </script>

    <form action="/pay" method="POST">
        <script
                src="https://checkout.stripe.com/checkout.js"
                class="stripe-button"
                data-key="pk_test_6pRNASCoBOKtIshFeQd4XMUh"
                data-image="/images/Zamia.gif"
                data-name="Humble Code"
                data-description="2 widgets ($20.00)"
                data-amount="2000">
        </script>
    </form>

 </div>
</body>
</html>