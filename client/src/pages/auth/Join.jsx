import { useState } from 'react';
import axios from 'axios';

export default function Join() {
  const [inputValue, setInputValue] = useState({
    name: '',
    email: '',
    password: '',
    check: '',
  });

  let valid = false;
  console.log(valid);

  const validHandler = (value) => {
    if (value.trim().length > 0) {
      valid = true;
    }
  };

  const inputValueChangeHandler = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
    validHandler(e.target.name);
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    if (inputValue.password !== inputValue.check) {
      alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
      return;
    }

    const item = {
      name: inputValue.name,
      email: inputValue.email,
      password: inputValue.password,
    };

    await axios
      .post('http://211.41.205.19:8080/members/join', item)
      .then(() => {
        setInputValue({
          name: '',
          email: '',
          password: '',
          check: '',
        });
        alert('회원가입 되었습니다 ! 환영합니다 :)');
      })
      .catch((e) => {
        console.log('err', e);
        if (e.response.status === 409) {
          alert('이미 존재하는 이메일 입니다. 다른 이메일을 사용해주세요.');
        }
      });
  };
  return (
    <main>
      <p className="text-center sm:text-xl md:text-3xl">
        FLY AWAY에 회원가입 하기
      </p>
      <form
        className="flex flex-col items-center bg-gray"
        onSubmit={submitHandler}
      >
        <label htmlFor="name">이름</label>
        <input
          id="name"
          type="text"
          name="name"
          value={inputValue.name}
          onChange={inputValueChangeHandler}
        />

        <label htmlFor="email">이메일</label>
        <input
          id="email"
          type="email"
          name="email"
          value={inputValue.email}
          onChange={inputValueChangeHandler}
        />
        <label htmlFor="password">비밀번호</label>
        <input
          id="password"
          type="password"
          name="password"
          value={inputValue.password}
          onChange={inputValueChangeHandler}
        />
        <label htmlFor="check">비밀번호 확인</label>
        <input
          id="check"
          type="password"
          name="check"
          value={inputValue.check}
          onChange={inputValueChangeHandler}
        />
        <button className="rounded bg-pink px-1.5">회원가입</button>
      </form>
    </main>
  );
}
