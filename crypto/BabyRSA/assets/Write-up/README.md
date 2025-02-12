# BabyRSA
## Description
Everything is given.

Author: RandomTeuz

## Write-up
Challenge này cũng yêu cầu chúng ta crack thuật toán RSA nhưng các các thông tin cần để giải đều đã có sẵn trong các file nên cách làm cũng không quá phức tạp.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/54247611dd0fd56e59c18bc878c1925f3ae88c71/crypto/BabyRSA/assets/1.png)

Có thể thấy các biến n, d, e, c đều được cung cấp rải rác trong các file khác nhau. c được tìm thấy trong [ciphertext.txt](https://github.com/tlmt009147/2025-Tech-Test/blob/54247611dd0fd56e59c18bc878c1925f3ae88c71/crypto/BabyRSA/assets/ciphertext.txt)

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/54247611dd0fd56e59c18bc878c1925f3ae88c71/crypto/BabyRSA/assets/3.png)

Còn n, d trong file [private_key.perm](https://github.com/tlmt009147/2025-Tech-Test/blob/54247611dd0fd56e59c18bc878c1925f3ae88c71/crypto/BabyRSA/assets/private_key.pem)

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/54247611dd0fd56e59c18bc878c1925f3ae88c71/crypto/BabyRSA/assets/3.png)

e đã có sẵn trong chall.py. Cuối cùng chỉ cần viết 1 chương trình giải RSA đơn giản mà thôi. [Script here !!!](https://github.com/tlmt009147/2025-Tech-Test/blob/9aaa51bf8f0aba6ab156bcef4b233827c17801ab/crypto/BabyRSA/assets/solve.py)

![4](https://github.com/tlmt009147/2025-Tech-Test/blob/73015933ae083eacb81fff434af5bee8821d4873/crypto/BabyRSA/assets/4.png)

Sau khi giải, mình chưa được nhận flag đúng, thay vào đó là gợi ý dùng Ceasar Cipher nên mình tiếp tục dùng "CFX{Try_Caesar_Until_Correct_Flag_format}" để giải mã với shift bằng 3. Mình nhận ra điều này nhờ shift 3 chữ cái đầu thành FIA.

![5](https://github.com/tlmt009147/2025-Tech-Test/blob/b8bfa2569afb2e5563b77579c400c6aba7f131ac/crypto/BabyRSA/assets/5.png)

## Flag

FIA{Wub_Fdhvdu_Xqwlo_Fruuhfw_Iodj_irupdw}
