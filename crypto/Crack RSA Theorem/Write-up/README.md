# Crack RSA Theorem
## Description

Can you break this RSA challenge with a CeRTain theorem?

Author: laby

## Write-up

Đối với bài này thì hint đã có sẵn trên phần mô tả. Thuật toán được sử dụng là RSA và gợi ý về việc decrypt sử dụng một "CeRTain theorem" nào đó. Để phân tích rõ hơn, mình sẽ đi qua phần [đề bài](https://github.com/tlmt009147/2025-Tech-Test/blob/8dcecead28331df70395e1926f7ca4ac340b17b5/crypto/Crack%20RSA%20Theorem/assets/chall.py).

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/8dcecead28331df70395e1926f7ca4ac340b17b5/crypto/Crack%20RSA%20Theorem/assets/1.png)

Đầu tiên, lấy flag và chuyển đổi flag thành số nguyên. Giá trị e được lấy bằng 3 với note "# should be ok, right?", đây là một giá trị e khá nhỏ và dễ khiến cho thuật toán bị bẻ gãy.Sau đó m được mã hóa 3 lần với 3 n khác nhau. Mỗi n được tính bằng cách lấy tích của hai số nguyên tố ngẫu nhiên có 1024 bit. Tới đây thì flag đã được mã hóa xong

Đối vớ bài này mình sử dụng Chinese Remainder Theorem dể giải

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/8dcecead28331df70395e1926f7ca4ac340b17b5/crypto/Crack%20RSA%20Theorem/assets/2.png)

## Flag
FIA{175_d4n63r0u5_t0_g0_4l0n3?_t4k3_CRT}
