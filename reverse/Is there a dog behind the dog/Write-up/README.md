# Is there a dog behind the dog
## Description
The dog is not behind the dog, it is not even the dog

Author: Bory
## Write-up
Đầu tiên, mình tải file Dog.exe về và thực thi xem có chuyện gì xảy ra. Khi tải thì Windows Defender warn có virus nên bị chặn :<. Tuy nhiên mình tắt Firewall đi và tiếp tục cho chạy file.

![1](https://github.com/tlmt009147/2025vừa tạo. Tới đây thì mình đã hiểu "một DLL "độc hại" riêng khác" chính là cái file mà Dog.exe tạo ra, và đây cũng là chương trình được chạy để check flag 
chứ không phải hàm main. Để xác minh, mình đặt breakpoint để tìm hiểu xem file được tạo ra ở đâu.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/10.png)

Và sau khi debug, mình biết được thư mục, file được tạo ra và vị trí của nó là C:\tmp\ico.ico.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/12.png)

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/13.png)

Mình không biết file .ico là gì nên search thử trên mạng.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/14.png)

Sau khi mở bằng Image Viewer thì kết quả là đây ...

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/15.png)

Cảm giác bị bịp quá quen thuộc nên mình mở file ico.ico bằng notepad để check Header.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/16.png)

Rõ ràng như trời buổi sáng, file ico.ico là một file PE 64 bit không sai vào đâu được. Khỏi cần check qua Detect it easy, mình mở ida lên và reverse file ico.ico này.
Không quá bất ngờ khi file ico.ico giống y như đúc hàm main() của Dog.exe. Đây cũng là lí do mình bị đánh lừa rằng hàm main() đang chạy thay vì ico.ico.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/19.png)·

Tuy nhiên vẫn có nhưng điểm khác biệt rõ rệt.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/17.png)

![](https://github.com/tlmt009147/2025-Tech-Test/blob/87253df42c368fbcb0bb3a0793aa0d34778db27f/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/20.png)

Chuỗi dùng để chứa flag và key khác với Dog.exe và chiểu dài của flag là 36.
Lúc này sắp ra flag ròi thì mình bị dốt đột xuất nên lấy đoạn dưới đây tham chiếu trong Pseudocode làm key và cipher nên ra kết quả sai.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/2adf655b6cde4333db1339073adedcbe6b153655/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/18.png)

Để giải được, ta cần bê y nguyên các giá trị của các biến trong assembly ra chứ không lấy từ Pseudocode sẽ có sai số cao.
- Chú ý đoạn key: key này được format 64 bit tương đương 8byte, 4 byte cuối được gán giá trị 637733143 tương đương 26030917 trong hex, 2 byte kế được gán giá trị 1296 tương đương 510 trong hex, 2 byte đầu bỏ trống
  - LODWORD(v11) = 637733143;
  - WORD2(v11) = 1296;

Vì lý do trên nên thuật toán XOR hơi khác so với Dog.exe một tí. Lấy lần lượt 6 byte trong cipher để XOR với 6 byte trong key và lặp đi lặp lại cho đến khi hết 36 byte của cipher.
[Script here!!!](https://github.com/tlmt009147/2025-Tech-Test/blob/2adf655b6cde4333db1339073adedcbe6b153655/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/doge%2C.py)

![](https://github.com/tlmt009147/2025-Tech-Test/blob/2adf655b6cde4333db1339073adedcbe6b153655/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/21.png)

Và flag của chúng ta đã xuất hiện. 

## Flag
FIA{Y455_Th3_Doge_1s_b3hind_th3_Dog}
