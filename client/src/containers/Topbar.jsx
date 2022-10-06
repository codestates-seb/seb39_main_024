import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import instance from '../service/request';
import { isLoginState } from '../recoil/atoms/isLoginState';
import { authorizationState } from '../recoil/atoms/authorizationState';
import ProfileIcon from './components/ProfileIcon';
import homeLogo from '../images/logo_home.png';
import mypageLogo from '../images/logo_mypage.png';
import Button from './components/Button';
import Modal from './components/Modal';
import logoutIcon from '../images/logout.png';

export default function TopBar({ path }) {
  const isLogin = useRecoilValue(isLoginState);
  const token = useRecoilValue(authorizationState);

  const [modalOpen, setModalOpen] = useState(false);
  const menuHandler = () => {
    setModalOpen(!modalOpen);
  };

  const logoutHandler = async () => {
    try {
      await instance.post('/logout', '_', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      });
      alert('로그아웃 되었습니다 !');
      window.localStorage.clear();
      window.location.replace('/');
    } catch (err) {
      console.log('err', err);
    }
  };

  return (
    <>
      <header
        className={`flex flex-row items-center justify-between h-16 md:h-20 w-full ${
          path.includes('/mypage') ? 'bg-green' : 'fixed bg-pink'
        }`}
      >
        <div className="flex flex-row items-center">
          <button onClick={menuHandler}>
            <img
              className="m-2 h-7 w-7 md:h-9 md:w-9 md:hidden"
              src="https://img.icons8.com/ios-glyphs/60/000000/menu--v1.png"
              alt="menu"
            />
          </button>
          <Link to="/">
            <img
              className="h-10.5 w-36 md:m-2 md:h-14 md:w-48"
              src={path.includes('/mypage') ? mypageLogo : homeLogo}
              alt="logo"
            />
          </Link>
          <Button className="sm:hidden" link="/videos" str="운동하기" />
          <Button className="sm:hidden" link="/posts" str="공유하기" />
        </div>
        <div className="flex flex-row items-center">
          {!isLogin && (
            <>
              <Button link="/login" str="로그인" />
              <Button link="/join" str="회원가입" />
            </>
          )}
          {isLogin && (
            <>
              <ProfileIcon />
              <img
                className="m-2 cursor-pointer"
                src={logoutIcon}
                alt="logout"
                onClick={logoutHandler}
                aria-hidden="true"
              />
            </>
          )}
        </div>
      </header>
      {modalOpen && <Modal className="md:hidden" onMenu={menuHandler} />}
    </>
  );
}
