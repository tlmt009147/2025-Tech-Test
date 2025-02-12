# Is there a dog behind the dog
## Description
The dog is not behind the dog, it is not even the dog

Author: Bory
## Write-up
Đầu tiên, mình tải file Dog.exe về và thực thi xem có chuyện gì xảy ra. Khi tải thì Windows Defender warn có virus nên bị chặn :<. Tuy nhiên mình tắt Firewall đi và tiếp tục cho chạy file.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/1.png)

Xem ra file này dùng để check xem flag input vào có đúng hay không.

Tiếp theo mình đưa file này vào Detect it easy để xem file được tạo ra bằng ngôn ngữ nào.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/2.png)

Ngôn ngữ được xác định là C/C++ nên mình dùng ida freeware để reverse file này. Khi reverse ra assembly, mình dùng f5 để decompile sang Pseudocode C để phân tích.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/3.png)

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/4.png)

Từ 2 bức hình trên, ta có thể phân tích sơ bộ như sau:
- Biến v15 chứa chuỗi "shibaizdabest", đây dường như là key trong thuật toán XOR
- v16 chứa giá trị 64 bit được thể hiện ở hệ cơ số 16 (hex)
- từ v17 đến v25 chứa các giá trị được thể hiện là số nguyên nên có thể suy ra các biến này có kiểu dữ liệu 32 bit
- User input được chứa trong Buffer.
- Nếu Buffer có đọ dài khác 39 thì in ra "Grrr this is not a Flag !!!" và dừng chương trình
- Buffer sau đó được XOR lần lượt với key và so sánh từng byte với từng byte từ v16 đến v25, nếu không giống thì thoát vòng lặp và in ra "Grrr this is not a Flag !!!"

Sau khi phân tích thì mình bắt đầu viết script để XOR từng byte từ v16 đến v25 với key để in ra flag. [Script here](https://github.com/tlmt009147/2025-Tech-Test/blob/991b270e0b6a817af0e0b6057dbefe1c3a42475f/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/a.c)

Hehe mình compile và chạy thử script thì kết quả ra khá giống format của flag. 

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/5.png)

Tuy nhiên khi mình chạy Dog.exe và input flag này thì kết quả trả về là "Grrr this is not a Flag !!!". Để chắc ăn mình còn đi test flag thử trong challenge và mất 3 lượt submit :(((.
![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/6.png)

Tới đây mình buồn quá nên mình đi debug. Trong khi debug thì mình phát hiện ra một chi tiết lạ.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/7.png)

Mình đặt breakpoint ở 2 nhánh để xem khi input flag FIA{Nope_The_dog_is_not_behind_the_dog} vào thì chương trình chạy ra sao. Tuy nhiên, thay vì rẽ phải để in ra
"Grrr this is not a Flag !!!" như khi chạy trong CMD thì chương trình lại rẽ trái vào "Yataaa Flag is gooddd". Tới đây mình còn nguu nên chỉ có thể đoán
là có một chương trình nào đấy như Anti Debugger đã gây ra việc này, tuy nhiên mình không tìm ra được mấu chốt và flag thật nằm ở đâu do lúc này mình mặc định
chỉ có 1 file Dog.exe chạy với main(). Sau đó mình đi ngủ.

**Hint 1**: Có sự khác biệt giữa việc chạy chương trình bằng IDA và "double click"/ chạy file PE qua CMD đó là: chạy chương trình bằng IDA là bạn đang DEBUG nó. Chương trình biết nó đang bị DEBUG

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/cb91e21a83d24e0a3955b60e3573332f4f36e5b7/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/8.png)

Yay sau 23 tiếng thì đã có hint đầu tiên. Hint này giúp mình khẳng định lại giả thuyết ban đầu. Sau khi raise ticket nghe lời khuyên thì mình đi mò và thấy một
hàm tên TLSCallback có một dòng check IsDebuggerPresent. Mình đặt một cái breakpoint ở đây để test xem Hàm này có chạy trước hàm main hay không và kết quả cho thấy
hàm này chạy trước hàm main() và rẽ phải khi được debug. 
- Hàm IsDebuggerPresent hoạt động bằng cách trả về 0 vào eax nếu không có Debugger và khác 0 nếu có.
- Nếu khác 0 thì rẽ phải, chạy main() và kết thúc chương trình như trên.

Ủa vậy thì nếu khi chạy bằng CMD thì main() có được thực thi không, và chương trình chạy như thế nào. Nếu main có chạy thì sao lại trả về sai kết quả.
Lúc này mình định tìm cách rẽ sang trái bằng cách set IP nhưng không được nên buồn và đi ngủ.

**Hint 2**: Trong điều kiện không bị debug, thực chất Dog.exe không chạy một mình, nó còn chạy một DLL "độc hại" riêng khác :D

Sau gần 20 tiếng thì có Hint 2. Mà hint này làm mình càng hoang mang, cái dll được chạy cùng là cái nào nhỉ? Sau khi raise ticket, mình được khai sáng cho cách
rẽ trái để bypass IsDebuggerPresent là phải thay đổi giá trị thanh ghi thì mới rẽ được :o. Và mình mất 1 tiếng để tìm ra cách làm điều đó.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/11.png)

Sau khi thay đổi giá trị thanh ghi và rẽ trái thành công, mình phát hiện ra một điều: Hàm main() sẽ không bao giờ được chạy nếu IsDebuggerPresent trả về 0.
Để khai sáng bản thân, mình f5 và decompile TLSCallback để đọc Pseudocode. Vì hồ đồ nên đọc chả hiểu gì. Sau khi tự làm khó bản thân thì cuối cùng mình cũng tìm thấy
đoạn code rất đáng nghi.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/9.png) 

Đây là chỗ duy nhất mình hiếm hoi hiểu chuyện gì đang xảy ra. Tóm tắt sơ lược thì Dog.exe đang cố tạo một thư mục gì đó, và tạo một file gì đó trong thư mục vừa tạo đó.
Sau đó thì thực thi file vửa tạo. Tới đây thì mình đã hiểu "một DLL "độc hại" riêng khác" chính là cái file mà Dog.exe tạo ra, và đây cũng là chương trình được chạy để check flag 
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
Không quá bất ngờ khi file ico.ico giống y như dúc hàm main() của Dog.exe. Đây cũng là lí do mình bị đánh lừa rằng hàm main() đang chạy thay vì ico.ico.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/19.png)·

Tuy nhiên vẫn có nhưng điểm khác biệt rõ rệt.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/d1566bd025cbf7ee12bd1658d77e5a51387a2a55/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/17.png)

![](https://github.com/tlmt009147/2025-Tech-Test/blob/87253df42c368fbcb0bb3a0793aa0d34778db27f/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/20.png)

Chuỗi dùng để chứa flag và key khác với Dog.exe và chiểu dài của flag là 36.
Lúc này sắp ra flag ròi thì mình bị dốt đột xuất nên lấy đoạn dưới đây tham chiếu trong Pseudocode làm key và cipher nên ra kết quả sai.

![](https://github.com/tlmt009147/2025-Tech-Test/blob/2adf655b6cde4333db1339073adedcbe6b153655/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/18.png)

Để giải được, ta cần bê y nguyên các giá trị của các biến trong assembly ra chứ không lấy từ Pseudocode sẽ có sai só cao.
- Chú ý đoạn key: key này được format 64 bit tương đương 8byte, 4 byte cuối được gán giá trị 637733143 tương đương 26030917 trong hex, 2 byte kế được gán giá trị 1296 tương đương 510 trong hẽ, 2 byte đầu bỏ trống
  - LODWORD(v11) = 637733143;
  - WORD2(v11) = 1296;

Vì lý do trên nên thuật toán XOR hơi khác so với Dog.exe một tí. Lấy lần lượt 6 byte trong cipher để XOR với 6 byte trong key và lặp đi lặp lại cho đến khi hết 36 byte của cipher.
[Script here!!!](https://github.com/tlmt009147/2025-Tech-Test/blob/2adf655b6cde4333db1339073adedcbe6b153655/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/doge%2C.py)

![](https://github.com/tlmt009147/2025-Tech-Test/blob/2adf655b6cde4333db1339073adedcbe6b153655/reverse/Is%20there%20a%20dog%20behind%20the%20dog/assets/21.png)

Và flag của chúng ta đã xuất hiện. 

## Flag
FIA{Y455_Th3_Doge_1s_b3hind_th3_Dog}
