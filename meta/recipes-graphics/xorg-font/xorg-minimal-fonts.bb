SUMMARY = "Xorg minimal fonts data"
DESCRIPTION = "Minimal fonts required by X.org."
HOMEPAGE = "http://www.x.org"

SECTION = "x11/fonts"

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://../misc/fonts.dir;md5=82a143d94d6a974aafe97132d2d519ab \
                    file://../misc/cursor.pcf.gz;md5=40bc81001fef4c21ca08df4305014a2a"

SRC_URI = "file://misc"

SOURCE_DATE_EPOCH = "1613559011"

PE = "1"

inherit allarch features_check

# The font-alias requires x11 in DISTRO_FEATURES
REQUIRED_DISTRO_FEATURES = "x11"

S = "${UNPACKDIR}/misc"

PACKAGES = "${PN}"
FILES:${PN} = "${libdir}/X11/ ${datadir}/fonts/X11/"
RDEPENDS:${PN} += "font-alias"

do_install() {
	install -d ${D}/${datadir}/fonts/X11/misc
	for file in ${S}/*.gz ${S}/fonts.dir; do
		install -m 0644 "$file" ${D}/${datadir}/fonts/X11/misc/
	done
	# Pick a date/time as otherwise it would be the git checkout/modify time
	touch -d @1613559011 ${D}/${datadir}/fonts/X11/misc/*
	install -d ${D}/${libdir}/X11
	ln -sf ${datadir}/fonts/X11/ ${D}/${libdir}/X11/fonts -s
}
