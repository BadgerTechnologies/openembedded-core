SUMMARY = "Python HTTP for Humans."
HOMEPAGE = "https://requests.readthedocs.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI:append:class-nativesdk = " \
           file://environment.d-python3-requests.sh \
"

SRC_URI[sha256sum] = "55365417734eb18255590a9ff9eb97e9e1da868d4ccd6402399eaf68af20a760"

inherit pypi python_setuptools_build_meta

do_install:append:class-nativesdk() {
	mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
	install -m 644 ${UNPACKDIR}/environment.d-python3-requests.sh ${D}${SDKPATHNATIVE}/environment-setup.d/python3-requests.sh
}

RDEPENDS:${PN} += " \
    python3-certifi \
    python3-email \
    python3-json \
    python3-netserver \
    python3-pysocks \
    python3-urllib3 \
    python3-chardet \
    python3-idna \
    python3-compression \
"

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/python3-requests.sh"

CVE_PRODUCT = "requests"

BBCLASSEXTEND = "native nativesdk"
