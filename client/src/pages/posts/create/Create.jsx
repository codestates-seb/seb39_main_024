import React, { useState } from 'react';
import instance from '../../../service/request';

import Image from './Image';
import Category from './Category';

export default function Create() {
  const [category, setCategory] = useState(0);

  const [inputValue, setInputValue] = useState({
    title: '',
    content: '',
  });

  const [imgFile, setImgFile] = useState([]);

  // 제목, 내용 Input 값 핸들러
  const inputValueChangeHandler = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
  };

  // 폼 제출 핸들러
  const submitHandler = async (e) => {
    e.preventDefault();

    const categoryId = Number(category);

    if (categoryId === 0) {
      alert('카테고리를 선택해주세요 !');
      return;
    }

    if (inputValue.title.trim() === '') {
      alert('제목은 최소 한 글자 이상 적어주세요 !');
      return;
    }

    if (inputValue.content.trim() === '') {
      alert('내용은 최소 한 글자 이상 적어주세요 !');
      return;
    }

    const item = {
      categoryId: category,
      title: inputValue.title,
      content: inputValue.content,
    };

    try {
      await instance.post(`/board/${categoryId}`, item, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      alert('글이 등록되었습니다 !');
      window.location.replace('/posts');
    } catch (err) {
      console.log('err', err);
    }
  };

  return (
    <form
      className="border-solid bg-green py-5 px-10 h-screen overflow-scroll"
      onSubmit={submitHandler}
    >
      <section className="flex justify-between">
        <Category category={category} setCategory={setCategory} />
        <button type="submit" className="bg-pink px-2.5 rounded">
          글 등록
        </button>
      </section>
      <input
        className="w-full mt-7 p-1.5"
        name="title"
        value={inputValue.title}
        onChange={inputValueChangeHandler}
        placeholder="제목을 입력하세요"
      />
      <Image imgFile={imgFile} setImgFile={setImgFile} />
      <textarea
        className="w-full h-40 mt-7 p-1.5"
        name="content"
        value={inputValue.content}
        onChange={inputValueChangeHandler}
        placeholder="내용을 입력하세요"
      />
    </form>
  );
}
