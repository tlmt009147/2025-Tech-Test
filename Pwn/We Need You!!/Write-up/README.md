# We Need You!!
## Description
FIA club opens for registration. Hurry!! Fill in the form to join

Author: u53rr007

nc chall.fia.io.vn 8386

## Write-up
Challenge này có vẻ cũng giống như challenge trước, mặc dù không rõ là overflow đối với kiểu dữ liệu nào nhưng mình cứ nhập đại một chuỗi siêu dài để xem hành vi của chương trình.
Thật bất ngờ, chương trình trả về shell của máy remote.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/7cd985859619ebfb4457154b6bbd3d425616dafa/Pwn/We%20Need%20You!!/assets/1.png)

Có shell rồi thì mình đi khám phá. Sau khi đọc README, mình đoán có file ẩn nên dùng lệnh ls -a thì·thấy một file có tên là .flag. Khi cat ra thì thấy một dòng có vẻ
giống như một đoạn Hex. 

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/7cd985859619ebfb4457154b6bbd3d425616dafa/Pwn/We%20Need%20You!!/assets/2.png)

Mình đem đoạn Hex đó đổi sang Text thì được flag.

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/7cd985859619ebfb4457154b6bbd3d425616dafa/Pwn/We%20Need%20You!!/assets/3.png)

## Flag
FIA{0V3r_F10W_1S_34SY}
