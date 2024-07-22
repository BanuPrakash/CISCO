<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Form</title>
</head>
<body>
    <h1>Product Form</h1>
    <!-- POST request to servlet whose URL is /products -->
    <form method="post" action="/products">
        Name <input type="text" name="name" /> <br />
        Price <input type="number" name="price"> <br />
        <button type="submit">Add Product</button>
        <button type="reset">Reset</button>
    </form>
</body>
</html>