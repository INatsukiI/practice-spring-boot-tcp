import socket

def test_server_with_specification():
    server_address = ('localhost', 12345)

    # テストデータのリスト (メッセージ, 期待されるレスポンス)
    test_cases = [
        (b'\x2B\x01\x02', b'A\x03'),  # 正常な加算 1 + 2 = 3
        (b'\x2D\x03\x02', b'A\x01'),  # 正常な減算 3 - 2 = 1
        (b'\x2A\x01\x02', b'N\x00'),  # 無効な演算子
    ]

    all_tests_passed = True 

    for message, expected_response in test_cases:
        print(f"Sending message: {message}")

        with socket.create_connection(server_address) as sock:
            sock.sendall(message)

            response = sock.recv(1024)
            print(f"Received response: {response}")

            if response == expected_response:
                print(f"Test passed for message: {message}")
            else:
                print(f"Test failed for message: {message}")
                print(f"Expected: {expected_response}, but got: {response}")
                all_tests_passed = False 

    if all_tests_passed:
        print("All tests passed!")
    else:
        print("Some tests failed.")

if __name__ == "__main__":
    test_server_with_specification()