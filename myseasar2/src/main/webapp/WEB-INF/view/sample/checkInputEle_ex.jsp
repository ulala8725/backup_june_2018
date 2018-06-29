<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
<section>
    <form id="sampleForm">
        <label>
            半角英数字以外を入力してみよう
            <input
                type="text" id="input" placeholder="半角英数字で入力"
                pattern="^[0-9A-Za-z]+$" required>
        </label>
        <button type="submit" id="submit">送信</button>
    </form>
</section>

<script>
"use strict";
var form   = document.getElementById("sampleForm"),
    input  = document.getElementById("input"),
    submit = document.getElementById("submit");

input.addEventListener("input", function() {
    if (!input.value) {
        input.setCustomValidity("");
        submit.click();
        return;
    } else if (false === form.checkValidity()) {
        input.setCustomValidity("半角英数字以外の値が入力されました。");
        submit.click();
        return;
    }
    input.setCustomValidity("");
});
</script>

</body></html>