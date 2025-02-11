# HTTP - Directory indexing
## Description

Looks like you found a restricted area! But... is this really what you're looking for? ;)

Author: lognpm

[https://nothttp.fia.io.vn](https://nothttp.fia.io.vn)

## Write-up
Lúc mình viết write-up cho bài này thì hình như server đang bị lỗi nên sẽ không có đầy đủ hình ảnh các bước.

Đầu tiên khi truy cập đường link, mình thấy một video ca nhạc rất dễ thương. Sau khi inspect source code, mình có thấy một ghi chú khá đáng ngờ. Đoạn ghi chú có vẻ thể hiện một đường dẫn đến một file nào đó.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/af8c34cce79a08b5557a09a51962c73f42879996/Web/HTTP%20-%20Directory%20indexing/assets/1.png)

Sau khi thử đường dẫn trên, mình được trả về một trang không có gì nổi bật lắm, tuy nhiên mình có thể rút ra được có một thư mục có tên là admin.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/af8c34cce79a08b5557a09a51962c73f42879996/Web/HTTP%20-%20Directory%20indexing/assets/2.png)

Sau khi đổi đường dẫn sang thư mục admin, một trang thể hiện các thư mục con và file có sẵn. Hiện trong ảnh không thể hiện điều đó nhưng khi mình làm, mình chú ý đến thời gian mà các file và thư mục được chỉnh sửa lần cuối. Khi đó có một thư mục tên backup được chỉnh sửa khá gần đây nên mình chọn để đào sâu thêm.

![4](https://github.com/tlmt009147/2025-Tech-Test/blob/af8c34cce79a08b5557a09a51962c73f42879996/Web/HTTP%20-%20Directory%20indexing/assets/4.png)

Và thật may mắn, có 1 tệp tên pass.html ở trong thư mục này cũng mới được thay đổi không lâu. Sau khi truy cập tệp, trang trả về có vẻ bình thường nhưng mình đã inspect và tìm thấy Flag đã được giấu ở đây.

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/af8c34cce79a08b5557a09a51962c73f42879996/Web/HTTP%20-%20Directory%20indexing/assets/3.png)

Theo mình nhớ không lầm thì có một tệp tên flag.txt với dòng "This file is encrypted." dùng để đánh lừa. Tuy nhiên không có dấu hiệu có flag trong file đó nên mình đã bỏ qua.

## Flag
FIA{D1rect0ry_L1sting_3xp0sed}
