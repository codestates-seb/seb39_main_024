import React from 'react';
import Button from '../containers/components/Button';

export default function Home() {
  return (
    <article className="flex flex-col items-center mb-10">
      <section className="flex flex-col items-center m-5 p-5 bg-white">
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
      <section className="flex flex-col items-center m-5 p-5 bg-white">
        <p className="text-center text-lg">
          운동하기 게시판에서 다양한 운동 영상을 접해보세요.
        </p>
        <p className="text-center text-lg">
          다양한 운동 영상들이 준비되어 있습니다.
        </p>
        <img
          className="p-10 rounded"
          src="https://images.unsplash.com/photo-1607962837359-5e7e89f86776?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
          alt="img"
        />
        <Button
          className="bg-pink rounded"
          link="/videos"
          str="운동하러 가기"
        />
      </section>
      <section className="flex flex-col items-center m-5 p-5 bg-white">
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
        <Button
          className="bg-green rounded"
          link="/posts"
          str="공유하러 가기"
        />
      </section>
    </article>
  );
}
