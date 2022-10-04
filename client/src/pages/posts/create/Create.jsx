import React, { useState } from 'react';
import { useRecoilValue } from 'recoil';
import instance from '../../../service/request';
import { memberIdState } from '../../../recoil/atoms/memberIdState';
import Image from './Image';
import Category from './Category';

export default function Create() {
  const memberId = useRecoilValue(memberIdState);
  console.log(memberId);

  const [category, setCategory] = useState(0);

  const [inputValue, setInputValue] = useState({
    title: '',
    content: '',
  });

  const [imgFile, setImgFile] = useState({
    url: [],
    file: [],
  });

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
      title: inputValue.title,
      content: inputValue.content,
      memberId: memberId,
    };

    const formData = new FormData();
    formData.append(
      'createDto',
      new Blob([JSON.stringify(item)], { type: 'application/json' })
    );
    if (imgFile.file.length > 0) {
      imgFile.file.forEach((file) => formData.append('image', file));
    }

    try {
      await instance.post(`/board/${categoryId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
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
      method="post"
      encType="multipart/form-data"
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
