# Interesting Machine
## Description
Can you buy some (Japanese Videos) JVs using this JVs vending machine?

Author: u53rr007

nc chall.fia.io.vn 2204
## Write-up
Từ mô tả, mình netcat đến chall.fia.io.vn với port 2204. Sau đó mình được hỏi 2 câu để mua đồ nhưng câu 1 dường như không có tác dụng để tìm ra flag nên mình tập trung vào câu 2.
Challenge này có source code nhưng do mình đọc code ngu nên mình sẽ tiến hành vài phép thử trực tiếp luôn. Qua phần hint, mình đoán rằng lỗ hổng trong bài này có
liên quan đến Integer Overflow trong C. Để thử, mình thử nhập một số nguyên dương siêu to và nhận được ...

![3](https://github.com/tlmt009147/2025-Tech-Test/blob/1bb0829970982b991abc40ffa72f3a5a130f71ed/Pwn/Interesting%20Machine/assets/3.png)

Mình được trả về câu "It seems like you have entered negative quantities?! What's wrong with you huh?". Từ đây mình rút ra được 2 kết luận sơ bộ:

- Input của quantity được kiểm tra thông qua một hàm nào đó để đảm bảo input hợp lệ (lớn hơn 0)
- Integer Overflow có thể không được khai thác trực tiếp thông qua biến chứa quantity mà có thể gián tiếp thông qua biến khác. Có thể là nhân với 100K để so sánh với 500K chẳng hạn.

Tìm hiểu về Integer Overflow tại [đây](https://www.welivesecurity.com/2022/02/21/integer-overflow-how-it-occur-can-be-prevented/). Và tìm kiếm thêm về Integer Range trong C.

![2](https://github.com/tlmt009147/2025-Tech-Test/blob/1bb0829970982b991abc40ffa72f3a5a130f71ed/Pwn/Interesting%20Machine/assets/2.png)

Có thể thấy khi số nguyên có giá trị trên 2147483647, Integer Overflow xảy ra và sẽ biến đổi thành số âm, đây cũng là lý do cho việc trả về "It seems like you have entered negative quantities?! What's wrong with you huh?"
ở trên. Để giải challenge này, ta cần điền vào một số từ 0 đến 2147483647 và phải đủ lớn để gây ra Integer Overflow thông qua một biến hoặc phép tính khác. 
Ở đây mình chọn luôn số 2147483647 để thử và tadaaa, Flag đã hiện ra.

![1](https://github.com/tlmt009147/2025-Tech-Test/blob/1bb0829970982b991abc40ffa72f3a5a130f71ed/Pwn/Interesting%20Machine/assets/1.png)

## Flag
FIA{in73g3r_0v3rfl0w_15_fun_righ7}
