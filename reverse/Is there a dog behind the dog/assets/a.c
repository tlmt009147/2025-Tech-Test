#include <stdio.h>
#include <string.h>
#include <stdint.h>

int main() {
    // The XOR key
    const char* key = "shibaizdabest";
    int key_length = strlen(key);

    // The values we need to match after XOR
    uint64_t v16 = 0x10A062F19282135;
    uint32_t v17 = 369964606;
    uint32_t v18 = 235345707;
    uint32_t v19 = 622463037;
    uint32_t v20 = 974523914;
    uint32_t v21 = 18551057;
    uint32_t v22 = 490604039;
    uint32_t v23 = 104726802;
    uint16_t v24 = 5130;
    uint8_t v25 = 9;

    // The flag should be 39 characters
    char flag[40] = {0};  // +1 for null terminator

    // First, solve for v16 (first 8 bytes)
    for(int i = 0; i < 8; i++) {
        uint8_t v16_byte = (v16 >> (i * 8)) & 0xFF;
        flag[i] = v16_byte ^ key[i % key_length];
    }

    // Solve for v17 (next 4 bytes)
    for(int i = 0; i < 4; i++) {
        uint8_t v17_byte = (v17 >> (i * 8)) & 0xFF;
        flag[i + 8] = v17_byte ^ key[(i + 8) % key_length];
    }

    // Solve for v18
    for(int i = 0; i < 4; i++) {
        uint8_t v18_byte = (v18 >> (i * 8)) & 0xFF;
        flag[i + 12] = v18_byte ^ key[(i + 12) % key_length];
    }

    // Solve for v19
    for(int i = 0; i < 4; i++) {
        uint8_t v19_byte = (v19 >> (i * 8)) & 0xFF;
        flag[i + 16] = v19_byte ^ key[(i + 16) % key_length];
    }

    // Continue for v20-v24
    for(int i = 0; i < 4; i++) {
        uint8_t v20_byte = (v20 >> (i * 8)) & 0xFF;
        flag[i + 20] = v20_byte ^ key[(i + 20) % key_length];
    }

    for(int i = 0; i < 4; i++) {
        uint8_t v21_byte = (v21 >> (i * 8)) & 0xFF;
        flag[i + 24] = v21_byte ^ key[(i + 24) % key_length];
    }

    for(int i = 0; i < 4; i++) {
        uint8_t v22_byte = (v22 >> (i * 8)) & 0xFF;
        flag[i + 28] = v22_byte ^ key[(i + 28) % key_length];
    }

    for(int i = 0; i < 4; i++) {
        uint8_t v23_byte = (v23 >> (i * 8)) & 0xFF;
        flag[i + 32] = v23_byte ^ key[(i + 32) % key_length];
    }

    // v24 is 2 bytes
    for(int i = 0; i < 2; i++) {
        uint8_t v24_byte = (v24 >> (i * 8)) & 0xFF;
        flag[i + 36] = v24_byte ^ key[(i + 36) % key_length];
    }

    // v25 is 1 byte
    flag[38] = v25 ^ key[38 % key_length];

    printf("Flag: %s\n", flag);

    // Print hex values for verification
    printf("\nHex values of flag:\n");
    for(int i = 0; i < 39; i++) {
        printf("%02x ", (unsigned char)flag[i]);
    }
    printf("\n");

    return 0;
}