# chao mung toi lop FRS
## Description
Có một sự cố đã xảy ra với máy của tôi, dường như có một user mới được tạo trên máy, may thay đã có traffic của máy, hãy giúp Jerryyy thầy đù tìm ra cái gì đang xảy ra trên máy nhé.

P/s: Jerryyy thầy đù không biết gì cả, chỉ biết đọc traffic thôi.

Author: Jerryyy

## Write up
Sau khi mở file lên xem sơ qua thì mình thấy quá trời packet và không biết bắt đầu từ đâu.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/1.png)

Từ phần mô tả, mình thấy có vẻ như user đã tải file gì đó về máy, và file pcapng có thể đã capture được điều này. Để kiểm tra, mình vào mục Export Object -> HTTP để kiểm tra
thử xem có file gì được tải xuống không.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/2.png)

Mình thấy có file install.ps1 có vẻ đáng ngờ nên quyết định extract để kiểm tra thử. Sau khi kiểm tra thì mình thấy đau đầu, chóng mặt. Tuy nhiên sau khi kiên trì 
ngồi nhìn một lúc thì mình thấy file này là một file powershell script bình thường với cấu trúc quen thuộc $var =  ... gì đó, chỉ có cái là các biến có tên dài và gây 
nhiễu. Sau đó mình dành thêm thời gian format lại file cho dễ đọc và phân tích.

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/3.png)

![4](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/4.png)

Khúc này nhìn bằng mắt thường hoặc sử dụng ChatGPT để giải thích thì flow của file install.ps1 được giải thích như sau:
- Đầu tiên tải file http://0x2250F19E/canonical (http://34.80.241.158/canonical). File này được mã hóa bằng thuật toán AES.
- Sử dụng Key = 's7Kw9F3pL2DhT5Nm1X8ZqY6Va0R3Gjk9', Vector = 'F2a8K7dG1N9X3mZ5' và mode CBC để giải mã file canonical vừa tải vào một file mới trong thư mục temp\.
- Sau khi giải mã thì thêm đuôi ".exe" vào file vừa tạo và thực thi.

Tiếp theo, mình extract file canonical nêu trên từ Wireshark và mang lên Cyberchef để giải mã.

![6](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/6.png)

Sử dụng Key = 's7Kw9F3pL2DhT5Nm1X8ZqY6Va0R3Gjk9', Vector = 'F2a8K7dG1N9X3mZ5' ở đinh dạng UTF8, mode CBC, input output là RAW, file canonical được decrypt và thể 
hiện rõ là 1 file thực thi ".exe" ngay trong metadata.

![5](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/5.png)

Xong khúc này mình không biết làm gì nữa và đi raise ticket. Sau khi được thông não thì mình tải file xuống, quăng vào "Detect it easy" để xác định ngôn ngữ lập trình được sử dụng.

![7](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/7.png)

Có thể thấy file này được lập trình bằng C# nên mình dùng tool dnspy để reverse.

![8](https://github.com/tlmt009147/2025-Tech-Test/blob/79fa7a369d19386eef8edf95d6d14ba6269899b7/forensics/chao%20mung%20toi%20lop%20FRS/assets/8.png)

Cái tên class với cái tên hàm nghe hơi khả nghi nên có thể coi tới bước này là chưa có gì sai. Xem tiếp thì ta thấy có một lệnh điều kiện để check Debugger.
Nếu không có Debugger, chương trình sẽ bắt đầu XOR từng byte trong chuỗi array với 64 và đưa vào hàm jerryyyandbory.

![13](https://github.com/tlmt009147/2025-Tech-Test/blob/1fcee1f5da36c313088d3ba3ce508f42bb524bf7/forensics/chao%20mung%20toi%20lop%20FRS/assets/13.png)

![9](https://github.com/tlmt009147/2025-Tech-Test/blob/1fcee1f5da36c313088d3ba3ce508f42bb524bf7/forensics/chao%20mung%20toi%20lop%20FRS/assets/9.png)

Mình đọc không hiểu hàm jerryyyandbory làm gì với đống byte sau khi XOR nhưng linh tính mách bảo mình đem array byte ban đầu đi XOR với 64 thử xem có gì thú vị không.
[Script here!!!](https://github.com/tlmt009147/2025-Tech-Test/blob/1fcee1f5da36c313088d3ba3ce508f42bb524bf7/forensics/chao%20mung%20toi%20lop%20FRS/assets/xor.py)

Và thật may mắn, flag đã xuất hiện :> 
![10](https://github.com/tlmt009147/2025-Tech-Test/blob/1fcee1f5da36c313088d3ba3ce508f42bb524bf7/forensics/chao%20mung%20toi%20lop%20FRS/assets/10.png)

## Flag
FIA{https://www.youtube.com/watch?v=bc0KhhjJP98}







