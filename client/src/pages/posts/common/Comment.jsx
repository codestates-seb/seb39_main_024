import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import instance from '../../../service/request';
import { memberIdState } from '../../../recoil/atoms/memberIdState';

export default function Comment({ items, postRead }) {
  const memberId = useRecoilValue(memberIdState);
  const [isEdit, setIsEdit] = useState(false);
  const [editValue, setEditValue] = useState(items.content);

  const date = items.createdAt.split('T');

  const userComment = Number(memberId) === items.memberId;

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
    let item = { memberId: memberId };
    try {
      await instance.delete(
        `/board/${postRead.boardId}/comment/${items.commentId}`,
        { data: item },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
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
      memberId: memberId,
      boardId: postRead.boardId,
      content: editValue,
    };
    try {
      await instance.patch(
        `/board/${postRead.boardId}/comment/${items.commentId}`,
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
    <section className="mt-2">
      <div className="flex justify-between mb-2">
        <div>
          <span className="mr-2">&#128100; {items.memberName}</span>
          <span className="mr-2">&#124;</span>
          <span className="ml-1">{`${date[0]} ${date[1].slice(0, 5)}`}</span>
        </div>
        {userComment && (
          <>
            {isEdit && (
              <div>
                <button onClick={cancelHandler}>취소</button>
                <button className="ml-2" onClick={completeHandler}>
                  완료
                </button>
              </div>
            )}
            {!isEdit && (
              <div>
                <button onClick={isEditHandler}>수정</button>
                <button className="ml-2" onClick={deleteHandler}>
                  삭제
                </button>
              </div>
            )}
          </>
        )}
      </div>
      {isEdit && (
        <>
          <input
            className="w-full border-solid border border-slate-500 rounded-md p-2"
            value={editValue}
            onChange={editValueHandler}
          />
        </>
      )}
      {!isEdit && <p className="py-1 ml-1">{items.content}</p>}
    </section>
  );
}
