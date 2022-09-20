import { Suspense, lazy } from 'react';
import { Route, Routes } from 'react-router-dom';
import { RecoilRoot } from 'recoil';

const Calendar = lazy(() => import('./pages/mypage/Calendar'));
const DeleteAccount = lazy(() => import('./pages/mypage/DeleteAccount'));
const Edit = lazy(() => import('./pages/mypage/Edit'));
const MyPage = lazy(() => import('./pages/mypage/MyPage'));
const PostsAll = lazy(() => import('./pages/posts/All'));
const VideosAll = lazy(() => import('./pages/videos/All'));
const Free = lazy(() => import('./pages/posts/Free'));
const Meal = lazy(() => import('./pages/posts/Meal'));
const Record = lazy(() => import('./pages/posts/Record'));
const Popularity = lazy(() => import('./pages/videos/Popularity'));
const Stretching = lazy(() => import('./pages/videos/Stretching'));
const Training = lazy(() => import('./pages/videos/Training'));
const Navbar = lazy(() => import('./containers/Navbar'));
const Home = lazy(() => import('./pages/Home'));
const Join = lazy(() => import('./pages/auth/Join'));
const Login = lazy(() => import('./pages/auth/Login'));
const Footer = lazy(() => import('./containers/Footer'));
const Layout = lazy(() => import('./containers/Layout'));
const Topbar = lazy(() => import('./containers/Topbar'));

function App() {
  return (
    <RecoilRoot>
      <Suspense fallback={<p>로딩중</p>}>
        <Topbar />
        <Navbar />
        <Layout>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/join" element={<Join />} />
            <Route path="/videos" element={<VideosAll />} />
            <Route path="/videos/popularity" element={<Popularity />} />
            <Route path="/videos/training" element={<Training />} />
            <Route path="/videos/stretching" element={<Stretching />} />
            <Route path="/posts" element={<PostsAll />} />
            <Route path="/posts/free" element={<Free />} />
            <Route path="/posts/meal" element={<Meal />} />
            <Route path="/posts/record" element={<Record />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/mypage/calendar" element={<Calendar />} />
            <Route path="/mypage/edit" element={<Edit />} />
            <Route path="/mypage/deleteaccount" element={<DeleteAccount />} />
          </Routes>
        </Layout>
        <Footer />
      </Suspense>
    </RecoilRoot>
  );
}

export default App;
