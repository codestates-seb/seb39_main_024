import { useState } from 'react';
import axios from 'axios';

export default function Comment({ items, postRead }) {
  const [isEdit, setIsEdit] = useState(false);
  const [editValue, setEditValue] = useState(items.content);

  // 수정 여부 핸들러
  const isEditHandler = () => {
    setIsEdit(true);
  };

  // 수정 인풋 값 핸들러
  const editValueHandler = (e) => {
    setEditValue(e.target.value);
  };

  // 취소 버튼 핸들러
  const cancelHandler = () => {
    setEditValue(items.content);
    setIsEdit(false);
  };

  // // 삭제 버튼 핸들러
  const deleteHandler = async () => {
    try {
      await axios.delete(
        `http://211.41.205.19:8080/board/${postRead.boardId}/comment/${items.commentId}`
      );
      alert('해당 댓글이 삭제되었습니다.');
      window.location.replace('/posts/read');
    } catch (err) {
      console.log('err', err);
    }
  };

  // 왼료 버튼 핸들러
  const completeHandler = async () => {
    if (editValue.trim() === '') {
      alert('최소 한 글자 이상 작성해주세요 !');
      return;
    }

    let item = {
      commentId: items.commentId,
      content: editValue,
    };
    try {
      await axios.patch(
        `http://211.41.205.19:8080/board/${postRead.boardId}/comment/${items.commentId}`,
        item,
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      alert('해당 댓글이 수정되었습니다.');
      window.location.replace('/posts/read');
    } catch (err) {
      console.log('err', err);
    }
  };

  return (
    <section className="bg-white">
      <div className="flex justify-between">
        <div>
          <span className="mr-2 ml-1">👤 작성자</span>
          <span className="mr-2">&#124;</span>
          <span className="ml-1">{items.createdAt}</span>
        </div>
        {isEdit && (
          <div>
            <button className="mr-1" onClick={cancelHandler}>
              취소
            </button>
            <button className="mr-1" onClick={completeHandler}>
              완료
            </button>
          </div>
        )}
        {!isEdit && (
          <div>
            <button className="mr-1" onClick={isEditHandler}>
              수정
            </button>
            <button className="mr-1" onClick={deleteHandler}>
              삭제
            </button>
          </div>
        )}
      </div>
      {isEdit && (
        <>
          <input value={editValue} onChange={editValueHandler} />
        </>
      )}
      {!isEdit && <p className="ml-1">{items.content}</p>}
    </section>
  );
}
