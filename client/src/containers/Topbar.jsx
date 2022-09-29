import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import homeLogo from '../images/logo_home.png';
import mypageLogo from '../images/logo_mypage.png';
import Button from '../components/Button';
import Modal from '../components/Modal';

export default function TopBar({ path }) {
  const [modalOpen, setModalOpen] = useState(false);
  const menuHandler = () => {
    setModalOpen(!modalOpen);
  };

  return (
    <>
      <header
        className={`flex flex-row items-center justify-between h-16 md:h-20 w-full ${
          path.includes('/mypage') ? 'bg-green' : 'bg-pink'
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
          <Button link="/login" str="로그인" />
          <Button link="/join" str="회원가입" />
        </div>
      </header>
      {modalOpen && <Modal className="md:hidden" onMenu={menuHandler} />}
    </>
  );
}
