import { useState } from 'react';
import { useSetRecoilState } from 'recoil';
import { memberIdState } from '../../recoil/atoms/memberIdState';
import { isLoginState } from '../../recoil/atoms/isLoginState';
import { authorizationState } from '../../recoil/atoms/authorizationState';
import { useNavigate } from 'react-router-dom';
import instance from '../../service/request';

export default function Login() {
  const navigate = useNavigate();

  const [inputValue, setInputValue] = useState({
    email: '',
    password: '',
  });

  const setMemberId = useSetRecoilState(memberIdState);
  const setIsLogin = useSetRecoilState(isLoginState);
  const setAuthorization = useSetRecoilState(authorizationState);

  const inputValueChangeHandler = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    const item = {
      email: inputValue.email,
      password: inputValue.password,
    };

    await instance
      .post('/login', item, {
        headers: {
          withCredentials: true,
          'Content-Type': 'application/json',
        },
      })
      .then((res) => {
        // console.log(res.headers.memberId);
        setInputValue({
          email: '',
          password: '',
        });
        setIsLogin(true);
        setMemberId(res.headers.memberid);
        setAuthorization(res.headers.authorization);
        console.log(res);
        alert('로그인 되었습니다.');
        navigate('/');
      })
      .catch((e) => {
        console.log('err', e);
      });
  };
  return (
    <div className="flex flex-col items-center justify-center my-8">
      <p className="mb-8 s:text-2xl text-3xl font-bold">
        FLY AWAY에 로그인 하기
      </p>
      <div className="flex flex-col items-center justify-center w-1/2 sm:w-4/5 bg-pale_pink rounded-lg">
        <form className="flex flex-col w-4/5 my-3" onSubmit={submitHandler}>
          <label className="h-10 leading-10" htmlFor="email">
            이메일
          </label>
          <input
            className="mb-3 h-10 rounded-md"
            id="email"
            type="email"
            name="email"
            value={inputValue.email}
            onChange={inputValueChangeHandler}
          />
          <label className="h-10 leading-10" htmlFor="password">
            비밀번호
          </label>
          <input
            className="mb-3 h-10 rounded-md"
            id="password"
            type="password"
            name="password"
            value={inputValue.password}
            onChange={inputValueChangeHandler}
          />
          <button className="my-6 h-10 bg-green rounded-md font-bold">
            로그인
          </button>
        </form>
      </div>
    </div>
  );
}
