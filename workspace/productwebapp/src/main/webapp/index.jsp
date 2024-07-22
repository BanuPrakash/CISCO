<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Application</title>
</head>
<body>

    <h1>Product Application, Welcome ${user}</h1>
    <div>
        <a href="logout">Logout</a>
    </div>
    <div>
        ${param.msg}
    </div>
    <!-- GET request to servlet whose URL pattern is /products-->
    <a href="products">Get all Products</a> <br />
    <!-- redirect to HTML page -->
    <a href="productForm.html">Add a new Product</a>
</body>
</html>