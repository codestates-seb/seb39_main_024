import React from 'react';
import { useRecoilValue } from 'recoil';
import Button from '../containers/components/Button';
import { isLoginState } from '../recoil/atoms/isLoginState';
import calenderImg from '../images/calender.png';
import goalImg from '../images/goal.png';

export default function Home() {
  const isLogin = useRecoilValue(isLoginState);
  return (
    <article className="flex flex-col items-center mb-10">
      <section className="flex flex-col items-center m-5 p-5">
        <h1 className="sm:text-3xl text-4xl text-center mt-5 mb-20">
          Flyaway에 오신 걸 환영합니다 !
        </h1>
        <p className="sm:text-xl text-2xl text-center p-2">
          Flyaway는 운동 영상을 시청하며{' '}
          <span className="bg-pink">운동 의지</span>를 높이고,
        </p>
        <p className="sm:text-xl text-2xl text-center p-2">
          운동한 것을 공유함으로써 <span className="bg-green">성취감</span>을
          얻을 수 있는
        </p>
        <p className="sm:text-xl text-2xl text-center p-2">
          웹 사이트 서비스입니다.
        </p>
      </section>
      <section className="flex flex-col items-center m-5 p-5">
        <p className="text-center text-lg">
          운동하기 게시판에서 다양한 운동 영상을 접해보세요.
        </p>
        <p className="text-center text-lg">
          다양한 운동 영상들이 준비되어 있습니다.
        </p>
        <img
          className="p-10 rounded "
          src="https://images.unsplash.com/photo-1607962837359-5e7e89f86776?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
          alt="img"
        />
        <Button
          className="bg-pink rounded"
          link="/videos"
          str="운동하러 가기"
        />
      </section>
      <section className="flex flex-col items-center m-5 p-5">
        <p className="text-center text-lg">
          공유하기 게시판에서 열심히 운동한 것을 공유해보세요.
        </p>
        <p className="text-center text-lg">
          다양한 사람들과 운동 이야기도 나누어봅시다.
        </p>
        <img
          className="p-10 rounded"
          src="https://images.unsplash.com/photo-1456324504439-367cee3b3c32?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTQxfHxwb3N0fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"
          alt="img"
        />
        <Button className="bg-pink rounded" link="/posts" str="공유하러 가기" />
      </section>
      <section className="flex flex-col items-center m-5 p-5">
        <p className="text-center text-lg">
          마이페이지에서 운동 기록을 확인해보세요.
        </p>
        <p className="text-center text-lg">
          하루 운동 시간에 따라 캘린더 배경색이 달라집니다.
        </p>
        <img className="p-10 rounded" src={calenderImg} alt="img" />
        <p className="text-center text-lg">
          누적 운동 시간에 따라 주어지는 스티커를 받고, 의지를 불태워봅시다.
        </p>
        <p className="text-center text-lg my-3">스티커 획득 기준 시간</p>
        <p className="text-center text-lg">20시간 - 달리기 스티커</p>
        <p className="text-center text-lg">50시간 - 줄넘기 스티커</p>
        <p className="text-center text-lg">80시간 - 덤벨 스티커</p>
        <p className="text-center text-lg">100시간 - 메달 스티커</p>
        <img className="p-10 rounded" src={goalImg} alt="img" />
        {isLogin && (
          <Button
            className="bg-green rounded"
            link="/mypage"
            str="마이페이지 가기"
          />
        )}
        {!isLogin && (
          <Button
            className="bg-green rounded"
            link="/login"
            str="로그인 하러 가기"
          />
        )}
      </section>
    </article>
  );
}
