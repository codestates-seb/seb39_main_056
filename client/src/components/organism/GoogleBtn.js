import { useRef } from 'react';
import useScript from '../../hooks/useScript';

const GoogleBtn = ({ onGoogleSignIn, text }) => {
  const googleSignInButton = useRef(null);

  useScript('https://accounts.google.com/gsi/client', () => {
    window.google.accounts.id.initialize({
      client_id: process.env.REACT_APP_CLIENT_ID,
      callback: onGoogleSignIn,
    });
    window.google.accounts.id.renderButton(googleSignInButton.current, {
      size: 'large',
      text,
      width: '400',
    });
  });

  return <div ref={googleSignInButton}></div>;
};

export default GoogleBtn;
