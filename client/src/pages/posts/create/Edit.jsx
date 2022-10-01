import React, { useState } from 'react';
import { useRecoilValue } from 'recoil';
import instance from '../../../service/request';
import { postReadState } from '../../../recoil/selectors/postReadState';
import { categoryIdState } from '../../../recoil/atoms/categoryIdState';

import Image from './Image';
import Category from './Category';

export default function Edit() {
  const postRead = useRecoilValue(postReadState);
  const categoryRead = useRecoilValue(categoryIdState);

  const [category, setCategory] = useState(categoryRead);

  const [inputValue, setInputValue] = useState({
    title: postRead.title,
    content: postRead.content,
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

    let item = {
      categoryId: category,
      boardId: postRead.boardId,
      title: inputValue.title,
      content: inputValue.content,
    };

    const formData = new FormData();
    formData.append(
      'updateDto',
      new Blob([JSON.stringify(item)], { type: 'application/json' })
    );
    if (imgFile.file.length > 0) {
      imgFile.file.forEach((file) => formData.append('image', file));
    }

    try {
      await instance.patch(`/board/${postRead.boardId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      alert('글이 수정되었습니다 !');
      window.location.replace('/posts/read');
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
          글 수정
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
