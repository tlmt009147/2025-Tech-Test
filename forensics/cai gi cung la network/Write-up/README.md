# cai gi cung la network
## Description
Thật sự, tất cả đều có thể capture được sao =))) ?

Author: Jerryyy

## Write-up
Nhìn sơ qua thì thấy file này có vẻ capture tín hiệu từ USB. Nhưng khi vào thử 1 Packet Descriptor Response DEVICE, mình thấy idProduct ghi là Keyboard nên rất có thể
đây là file ghi keystrokes từ bàn phím.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/1.png)

Sau khi lướt file thêm vài lần nữa, mình thấy các packet đi từ thiết bị ngoại vi đến host có length là 35 byte thường có 1 giá trị gọi là HID data trong phần
thông tin của packet. Tìm thêm thông tin trên mạng về [HID](https://en.wikipedia.org/wiki/Human_interface_device)(Human Interface Device) và HID Usage Table, mình biết được
giá trị của HID data đại diện cho các ký tự.

Mình tìm thử trên mạng xem có tool nào có thể dùng để parse HID data thành các ký tự hàng loạt không. Và mình tìm thấy tool [này](https://github.com/TeamRocketIst/ctf-usb-keyboard-parser)

Đầu tiên mình dùng filter để lọc ra các packet liên quan và trích xuất HID data của các packet thành một cột duy nhất.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/2.png)
![3](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/3.png)

Sau đó mình ẩn tất cả các côt, chỉ để lại cột HID data để export sang một file csv.

![4](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/4.png)
![5](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/5.png)

Sau khi có được file csv của cột HID data, mình loại bỏ các dấu """ và header để tạo ra format phù hợp để dùng tool. Kế tiếp mình copy nội dung file này vào 1 file khác
trên linux.
![7](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/7.png)

Vì hiện giờ file chưa được format đúng để dùng tool nên mình phải format lại để có dấu ":" giữa mỗi 2 số.
![8](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/8.png)

Cuối cùng mình chạy tool in ra kết quả.
![9](https://github.com/tlmt009147/2025-Tech-Test/blob/33a18ff21a241f8abfc7c9959b58fc806efc6c18/forensics/cai%20gi%20cung%20la%20network/assets/9.png)

## Flag
FIA{https://www.youtube.com/watch??v=NHEst6ZzlMk}







