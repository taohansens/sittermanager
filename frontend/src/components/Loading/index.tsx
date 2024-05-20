const LoadingBanner = () => {
    return (
        <div className="alert alert-danger d-flex align-items-center" role="alert">
                  <span className="spinner-border spinner-border-sm me-2"
                        role="status"></span> Aguarde, estamos carregando os usuários !
        </div>
    );
}
export default LoadingBanner;