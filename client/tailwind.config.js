/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: {
        pink: '#FFDADA',
        pale_pink: '#FFEAEA',
        light_pink: '#FFEDED',
        green: '#A8F1DE',
        gray: '#F4F4F4',
        deep_green: '#02CD9A',
      },
      width: {
        480: '480px',
        768: '768px',
      },
      height: {
        768: '768px',
        350: '350px',
        446: '446px',
      },
    },
    maxWidth: {
      480: '480px',
    },
    screens: {
      s: { max: '480px' },
      sm: { max: '767px' },
      // => @media (max: '767px') { ... }
      md: { min: '768px' },
    },
    backgroundImage: {
      'split-green-pink': 'linear-gradient(to top, #FFDADA 55% , #A9F1DF 45%);',
      'sm-split-green-pink':
        'linear-gradient(to top, #FFDADA 50% , #A9F1DF 50%);',
    },
  },
  plugins: [],
};
