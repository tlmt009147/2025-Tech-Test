# steg is steg not forensics
## Description
https://www.youtube.com/shorts/GkvGfeTyo98

Author: _SawG23
## Write-up
Đầu tiên mình tải file chall.wav về nghe thử và thấy có một đoạn âm thanh bị thay đổi. Sau đó mình dùng Sonic Visualizer và apply Layer Spectrogram vào để xem có thông tin nào được ẩn không. Không nằm ngoài dự đoán, có một đường link được nhúng vào file này.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/1.png)

Thông tin được ẩn là một đường link dẫn đến trang github nào đó. Khi truy cập đường link, mình thấy có 3 file, sau khi xem qua từng file thì mình tải về file hình ảnh
treasure.jpg

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/2.png)

![treasure](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/treasure.jpg)

Sau đó, mình dùng exiftool để lấy thông tin từ file xem có gì khả nghi không.

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/3.png)

Tới đây có 2 hướng giải cho challenge này.

### Hướng 1 (Do author sơ suất nên mới làm được)
Thông qua thông tin cung cấp của exiftool, mình thấy 1 dòng các giá trị hex nghi là flag ở mục title. Copy dòng hex đó chuyển thành text thì ra 1 dòng ký tự
nhìn hơi giống base64: RklBe1kwdV9oQHZlX2ZPdW5kX3RoM190cmVhc3VyZV9vZl9FYWdsZWVlZWV9. Đem dòng text đi giải mã base64 thì ra được flag.

![7](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/7.png)

### Hướng 2 (Đây mới là hướng author muốn)
![3](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/3.png)

Từ thông tin trên, mình thấy mục exif image height và weight có giá trị là 2300 và 1000, trong khi image size lại là 1000x956. Điều này rất đáng ngờ và thường là dấu hiệu
của Metadata Manipulation dùng để thay đổi thông tin của metadata. Đối với bài của chúng ta, height của ảnh đã được thay đổi từ 2300 thành 956, điều này đánh lừa
các phần mềm mở ảnh để chỉ hiển thị một phần của ảnh. Bài tham khảo về Metadata Manipulation có thể xem thêm ở [đây](https://cyberhacktics.com/hiding-information-by-changing-an-images-height/)

Vậy nhiệm vụ hiện giờ là phải chỉnh metadata trở về lại như cũ thì hình ảnh mới được hiện hết. Để làm điều này, mình quăng file lên
cyberchef và chọn From Hex để dễ dàng phân tích.

![5](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/5.png)

Sau đó mình copy Output và mở tab mới paste vào để làm Input mới.

Dựa vào thông tin dưới đây, mình tìm cụm ff c0 trong input mới. Đây là nơi chứa metadata của hình ảnh.

![4](https://github.com/tlmt009147/2025-Tech-Test/blob/cb5f39a9237c70ec1a2791f8f6e3aba0f5212284/forensics/steg%20is%20steg%20not%20forensics/assets/4.png)

Có thẻ thể thấy, ở vị trí của height đang để giá trị là "03 bc" tương đương với 956 trong hệ thập phân. Mình chỉ cần chỉnh "03 bc" thành "08 fc" 
tương đương với 2300 là có thể khôi phục hình ảnh ban đầu.
Sau đó mình chon From Hex và Render image để tái tạo lại ảnh và tải xuống là xong.

![6](https://github.com/tlmt009147/2025-Tech-Test/blob/92416fec70e6624ceda7ae2c85a628bcf3c0541b/forensics/steg%20is%20steg%20not%20forensics/assets/6.png)

Ảnh được tải ở [đây](https://github.com/tlmt009147/2025-Tech-Test/blob/92416fec70e6624ceda7ae2c85a628bcf3c0541b/forensics/steg%20is%20steg%20not%20forensics/assets/download.jpg)

Từ ảnh ta có thê lấy được chuỗi y chư cách 1 và làm tương tự để ra flag

## Flag
FIA{Y0u_h@ve_fOund_th3_treasure_of_Eagleeeee}


