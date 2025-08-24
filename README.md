# BabyTEA

Simplified Feistel block cipher that demonstrates how encryption and decryption mirror each other using shifts, additions, XOR, and key mixing


## Contributors

This project is a collaborative effort by a team of six students from the CS3250 class of Group 2:

- Riley McKenzie
- Elicia Perez

## Description

Encryption:

- Splits a 64 bit plaintext block into 2 halves: L[0] and R[0]
- Uses a 128 bit key: K[0]...K[3] and 2 fixed constants: delta1 and delta2
- Runs 2 Feistel rounds, where the right half goes through a custom round function F that combines shifts, additions, and XOR wiht key material
- Produces a 64 bit ciphertext block: L[2] and R[2]

Decryption:

- Accepts the same 128 bit key and the cipher halves: L[2] and R[2]
- Runs the Feistel rounds in reverse, using subtraction instead of adition to invert the process
- Recovers the original plaintext block L[0] and R[0]


## Installation

### Prerequisites
Before you begin, ensure you have Java 8+ installed on your computer

### 1. Clone the Repository
```bash
git clone https://github.com/rileymck/BabyTEA.git

```

### 2. Compile the program
Make sure you have Java 8+ installed
``` bash
javac encryption.java decryption.java
```

### 3. Run the Encryption
``` bash
java encryption
```

### 4. Run the Decryption
``` bash 
java decryption
```

## Notes
- Inputs must be 8 digit hexadecimal string
- Keys and data are 32 bit words, combined into a 128 bit key and 64 bit blocks
