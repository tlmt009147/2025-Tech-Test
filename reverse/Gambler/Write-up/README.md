# Gambler
## Description

Beat me boi

Author: Bory

## Write-up
Challenge này cung cấp một file thực thi java tên [GamblingGuess.class](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/GamblingGuess.class).
Để reverse file này, mình sử dụng [java decompiler online](https://www.decompiler.com/).

Sau khi tham khảo [source code](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/GamblingGuess.java),
mình để ý 3 chi tiết chính mà mình đoán là liên quan đến quá trình tạo và giải mã flag.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/1.png)

Đầu tiên mình chú ý đến biến CIPHER chứa các giá trị là số nguyên, mình nghi ngờ đây là flag đã được mã hóa bằng thuật toán XOR.
Ở đây mình cũng chú ý dòng code đã highlight. Dòng này khởi tạo một đối tượng Random và sử dụng giá trị 2025L làm seed cho đối tượng đó. 
Khi sử dụng var2 để tạo các số ngẫu nhiên, chuỗi số sẽ luôn giống nhau mỗi khi chạy chương trình (miễn là seed là 2025L). 
Điều này giúp đảm bảo tính lặp lại trong các phép toán ngẫu nhiên muốn tái tạo kết quả ngẫu nhiên trước đó.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/2.png)

Tiếp theo mình bắt gặp đoạn code trên. Ta có thể thấy biễn var4 dùng để chứa 10 giấ trị ngẫu nhiên được tạo ra từ seed, và đương nhiên 10 giá trị ngẫu nhiên này sẽ giống nhau mỗi khi chương trình được khởi chạy.

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/3.png)

Tiếp theo, từng số trong CIPHER được XOR lần lượt với từng giá trị trong biến v4 và in ra màn hình với kiểu dữ liệu là char.

Có 2 cách để giải bài này. Cách 1 là sử dụng Random var2 = new Random(2025L); để có được 10 số nguyên dùng và nhập lần lượt vào các Round, sau khi nhập hêt 10 sẽ in ra flag.
Tuy nhiên mình đang sử dụng bảng java 8 trong khi file là được compile bằng java 17 nên mình sẽ tạo một file mới để decrypt ra flag, cũng là cách 2.
[Script here!!!](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/Decrypt.java)

![4](https://github.com/tlmt009147/2025-Tech-Test/blob/71ba375c97adbcae640801e477e1932459976411/reverse/Gambler/assets/4.png)

## Flag
FIA{G4mbl3_Lun4r_N3w_Ye4r_1s_n0t_3n0ugh}


