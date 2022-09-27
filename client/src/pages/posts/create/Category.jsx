import React from 'react';

export default function Category({ category, setCategory }) {
  const categoryHandler = (e) => {
    setCategory(e.target.value);
  };

  return (
    <select
      value={category}
      className="bg-pink p-1.5 rounded"
      onChange={categoryHandler}
    >
      <option value="0">카테고리</option>
      <option value="1">운동 기록</option>
      <option value="2">다이어트 식단</option>
      <option value="3">자유</option>
    </select>
  );
}
