import { Fragment } from 'react';

import { Swiper, SwiperSlide } from 'swiper/react'; // basic
import SwiperCore, { Navigation, Pagination } from 'swiper';
import 'swiper/swiper-bundle.min.css';
import 'swiper/swiper.min.css';
import 'swiper/components/navigation/navigation.min.css';
import 'swiper/components/pagination/pagination.min.css';

SwiperCore.use([Navigation, Pagination]);

export default function Image({ imgFile, setImgFile }) {
  // 이미지 업로드 버튼 핸들러
  const imgAddHandler = (e) => {
    const imgList = e.target.files;
    let imgUrlList = [...imgFile.url];
    let imgFileList = [...imgFile.file];

    for (let i = 0; i < imgList.length; i++) {
      const currentImageUrl = URL.createObjectURL(imgList[i]);
      imgUrlList.push(currentImageUrl);
      imgFileList.push(imgList[i]);
    }

    if (imgUrlList.length > 5) {
      imgUrlList = imgUrlList.slice(0, 5);
      alert('사진은 최대 5장까지 업로드 가능합니다.');
    }

    setImgFile({
      url: imgUrlList,
      file: imgFileList,
    });
  };

  // 이미지 삭제 핸들러
  const imgDeleteHandler = (id) => {
    setImgFile({
      url: imgFile.url.filter((_, index) => index !== id),
      file: imgFile.file.filter((_, index) => index !== id),
    });
  };

  return (
    <Fragment>
      <section className="mt-7">
        <label
          className="bg-pink p-1.5 rounded cursor-pointer"
          htmlFor="input-file"
          onChange={imgAddHandler}
        >
          사진 업로드
          <input
            className="hidden"
            type="file"
            id="input-file"
            multiple
            accept="image/*"
          />
        </label>
      </section>
      <section className="mt-7">
        {imgFile.url.length > 0 && (
          <Swiper
            modules={[Pagination, Navigation]}
            className="w-full h-96 bg-white"
            spaceBetween={10}
            centeredSlides={true}
            navigation
            pagination={{ clickable: true }}
            scrollbar={{ draggable: true }}
            breakpoints={{
              320: { slidesPerView: 1 },
              768: { slidesPerView: 2 },
              1024: { slidesPerView: 3 },
              1440: { slidesPerView: 4 },
            }}
          >
            {imgFile.url.map((img, id) => (
              <SwiperSlide
                key={id}
                className="p-3 m-0 flex flex-col justify-center items-center"
              >
                <img src={img} alt={`${img.url}-${id}`} className="w-60 h-60" />
                <button
                  type="button"
                  className="bg-pink w-20 mt-2.5"
                  onClick={() => imgDeleteHandler(id)}
                >
                  삭제
                </button>
              </SwiperSlide>
            ))}
          </Swiper>
        )}
        {imgFile.url.length === 0 && (
          <Swiper
            className="h-80 bg-white items-center"
            centeredSlides={true}
            slidesPerView={3}
          >
            <SwiperSlide className="flex flex-col justify-center items-center">
              업로드된 사진이 없습니다.
            </SwiperSlide>
          </Swiper>
        )}
      </section>
    </Fragment>
  );
}
